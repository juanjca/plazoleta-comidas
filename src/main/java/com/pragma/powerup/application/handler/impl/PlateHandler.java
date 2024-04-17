package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.ChangeStateOrder;
import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
import com.pragma.powerup.application.dto.request.PlateRequestDto;
import com.pragma.powerup.application.dto.request.StatePlateRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponse;
import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.application.exception.PlateNotExist;
import com.pragma.powerup.application.exception.RestaurantNotExist;
import com.pragma.powerup.application.exception.UserNotEmployee;
import com.pragma.powerup.application.handler.IPlateHandler;
import com.pragma.powerup.application.mapper.OrderResponseMapper;
import com.pragma.powerup.application.mapper.PlateRequestMapper;
import com.pragma.powerup.application.mapper.PlateResponseMapper;
import com.pragma.powerup.application.mapper.PlateUpdateRequestMapper;
import com.pragma.powerup.domain.api.IPlateServicePort;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.model.Plate;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.exception.NotOwnerOfRestaurant;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlateEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.OrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.OrderPlateEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.RestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlateHandler implements IPlateHandler {

    private final IPlateServicePort plateServicePort;
    private final PlateRequestMapper plateRequestMapper;
    private final PlateUpdateRequestMapper plateUpdateRequestMapper;
    private final PlateResponseMapper plateResponseMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IPlateRepository plateRepository;
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IOrderRepository orderRepository;
    private final OrderResponseMapper orderResponseMapper;

    @Override
    public void savePlate(PlateRequestDto plateRequestDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserEntity) authentication.getPrincipal()).getIdUser();

        Optional<RestaurantEntity> restaurantOptional = restaurantRepository.findById(plateRequestDto.getIdRestaurant());
        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotExist("The idRestaurant not found");
        }

        RestaurantEntity restaurantEntity = restaurantOptional.get();

        if (!restaurantEntity.getIdUser().equals(userId)) {
            throw new NotOwnerOfRestaurant();
        }

        Plate plate = plateRequestMapper.toPlate(plateRequestDto);

        plate.setState(true);
        plateServicePort.savePlate(plate);
    }

    @Override
    public void updatePlate(PlatePutRequestDto platePutRequestDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserEntity) authentication.getPrincipal()).getIdUser();

        Optional<PlateEntity> plateEntityOptional = plateRepository.findById(platePutRequestDto.getIdPlate());
        if (plateEntityOptional.isEmpty()) {
            throw new PlateNotExist("Plate not found");
        }

        PlateEntity plateEntity = plateEntityOptional.get();

        if (!plateEntity.getIdRestaurant().getIdUser().equals(userId)) {
            throw new NotOwnerOfRestaurant();
        }


        Plate oldPlate = plateServicePort.getPlate(platePutRequestDto.getIdPlate());
        Plate newPlate = plateUpdateRequestMapper.toPlateUpdate(platePutRequestDto);
        newPlate.setName(oldPlate.getName());
        newPlate.setIdCategory(oldPlate.getIdCategory());
        newPlate.setIdRestaurant(oldPlate.getIdRestaurant());
        newPlate.setUrlImage(oldPlate.getUrlImage());
        plateServicePort.updatePlate(newPlate);
    }

    @Override
    public void changeStatePlate(StatePlateRequestDto statePlateRequestDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserEntity) authentication.getPrincipal()).getIdUser();

        Optional<PlateEntity> plateEntityOptional = plateRepository.findById(statePlateRequestDto.getIdPlate());
        if (plateEntityOptional.isEmpty()) {
            throw new PlateNotExist("Plate not found");
        }

        PlateEntity plateEntity = plateEntityOptional.get();

        if (!plateEntity.getIdRestaurant().getIdUser().equals(userId)) {
            throw new NotOwnerOfRestaurant();
        }

        plateEntity.setState(statePlateRequestDto.getState());
        plateRepository.save(plateEntity);
    }


    @Override
    public PlateResponse getPlate(Long idPlate) {
        Plate plate = plateServicePort.getPlate(idPlate);
        return plateResponseMapper.toResponse(plate);
    }

    @Override
    public List<PlateResponse> getMenuForRestaurant(Long restaurantId) {
        List<Plate> plates = plateServicePort.getMenuForRestaurant(restaurantId);
        return plateResponseMapper.toResponseList(plates);
    }

    @Override
    public Page<List<Orders>> getOrderByState(ChangeStateOrder changeStateOrder, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long idUser = ((UserEntity) authentication.getPrincipal()).getIdUser();

        System.out.println("ID DE USER " + idUser);

        Optional<UserEntity> userEntityOptional = userRepository.findById(idUser);
        UserEntity userEntity = userEntityOptional.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        User user = userEntityMapper.toUserEntity(userEntity);

        if (!user.getIdRole().getIdRole().equals(3L)) {
            throw new UserNotEmployee("Disculpa. A esta consulta solo tiene acceso el empleado.");
        }

        Page<OrderEntity> orderPage = orderRepository.findByState(changeStateOrder.getState(), pageable);

        List<List<Orders>> orderListsPerPage = orderPage.getContent().stream()
                .map(orderEntity -> {
                    List<Orders> listOrders = new ArrayList<>();

                    Long idRestaurantOrder = orderEntity.getIdRestaurant();
                    Long idEmployeeOrder = orderEntity.getIdEmployee();
                    Long idClienteOrder = orderEntity.getIdClient();

                    Optional<UserEntity> userEntityOptionalOrder = userRepository.findById(idClienteOrder);
                    UserEntity userEntityOrder = userEntityOptionalOrder.orElseThrow(() -> new RuntimeException("Usuario del pedido no encontrado"));
                    User userOrder = userEntityMapper.toUserEntity(userEntityOrder);

                    Optional<RestaurantEntity> restaurantEntityOptionalOrder = restaurantRepository.findById(idRestaurantOrder);
                    RestaurantEntity restaurantEntityOrder = restaurantEntityOptionalOrder.orElseThrow(() -> new RuntimeException("Restaurante del pedido no encontrado"));
                    Restaurant restaurantOrder = restaurantEntityMapper.toRestaurantEntity(restaurantEntityOrder);

//                    Optional<UserEntity> employeeEntityOptionalOrder = userRepository.findById(idEmployeeOrder);
//                    UserEntity employeeEntityOptionarOrder = employeeEntityOptionalOrder.orElseThrow(() -> new RuntimeException("Empleado del pedido no encontrado"));
//                    User employeeOrder = userEntityMapper.toUserEntity(employeeEntityOptionarOrder);

                    OrderResponse orderResponse = new OrderResponse();
                    orderResponse.setIdPedido(orderEntity.getIdPedido());
                    orderResponse.setDateOrder(orderEntity.getDateOrder());
                    orderResponse.setIdClient(userOrder);
                    orderResponse.setIdRestaurant(restaurantOrder);
                    orderResponse.setIdEmployee(null);
                    orderResponse.setState(orderEntity.getState());
                    // Aquí deberías agregar la lista de platos al orderResponse si es necesario

                    Orders orders = orderResponseMapper.toOrder(orderResponse);
                    listOrders.add(orders);

                    return listOrders;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(orderListsPerPage, pageable, orderPage.getTotalElements());
    }


}

