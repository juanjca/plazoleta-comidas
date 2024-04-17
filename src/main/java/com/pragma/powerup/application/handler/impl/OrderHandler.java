package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.exception.RestaurantNotExist;
import com.pragma.powerup.application.exception.RolUserNotAdmitted;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.OrderRequestMapper;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.model.State;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.exception.ClientNotExistsException;
import com.pragma.powerup.infrastructure.exception.UserNotExistException;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.OrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final OrderRequestMapper orderRequestMapper;
    private final OrderEntityMapper orderEntityMapper;
    private final UserEntityMapper userEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IUserRepository userRepository;
    private final OrderPlateHandler orderPlateHandler;
    private final IOrderRepository orderRepository;

    @Override
    public void saveOrder(OrderRequestDto orderRequestDto) {

        Orders order;

        Long idRestaurant = orderRequestDto.getIdRestaurant();

        if (!restaurantRepository.existsById(idRestaurant)) {
            throw new RestaurantNotExist("The restaurant with id " + idRestaurant + " Not found");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long idClient = 0L;
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserEntity) {
                idClient = ((UserEntity) principal).getIdUser();
                Long typeUser = ((UserEntity) principal).getIdRole().getIdRole();
                if (!userRepository.existsById(idClient)) {
                    throw new UserNotExistException();
                }

                if (!typeUser.equals(4L)) {
                    throw new ClientNotExistsException("No eres un cliente");
                }
            }

            order = orderRequestMapper.toOrder(orderRequestDto);
            Optional<UserEntity> userOptional = userRepository.findById(idClient);
            UserEntity userEntity = userOptional.get();
            User user = userEntityMapper.toUserEntity(userEntity);

            order.setIdClient(user);
            orderServicePort.saveOrder(order);

            List<Long> ids = orderRequestDto.getIdPlate();

            for (Long idPlate : ids) {
                orderPlateHandler.saveOrderPlate(orderRequestDto, idPlate);
            }
        }
    }

    @Override
    public void selectOrder(Long idOrder, State state) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long idUser = ((UserEntity) authentication.getPrincipal()).getIdUser();

        Optional<UserEntity> userEntityOptional = userRepository.findById(idUser);
        UserEntity userEntity = userEntityOptional.get();
        User user = userEntityMapper.toUserEntity(userEntity);

        if (!user.getIdRole().getIdRole().equals(3L)) {
            throw new RolUserNotAdmitted("No tienes permitido entrar a esta endpoint");
        }

        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(idOrder);
        OrderEntity orderEntity = orderEntityOptional.get();
        Orders order = orderEntityMapper.toOrder(orderEntity);

        order.setState(state);
        orderRepository.save(orderEntityMapper.toOrderEntity(order));
    }


}

