    package com.pragma.powerup.application.handler.impl;

    import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
    import com.pragma.powerup.application.dto.request.PlateRequestDto;
    import com.pragma.powerup.application.dto.response.PlateResponse;
    import com.pragma.powerup.application.exception.PlateNotExist;
    import com.pragma.powerup.application.exception.RestaurantNotExist;
    import com.pragma.powerup.application.handler.IPlateHandler;
    import com.pragma.powerup.application.mapper.PlateRequestMapper;
    import com.pragma.powerup.application.mapper.PlateResponseMapper;
    import com.pragma.powerup.application.mapper.PlateUpdateRequestMapper;
    import com.pragma.powerup.domain.api.IPlateServicePort;
    import com.pragma.powerup.domain.model.Plate;
    import com.pragma.powerup.infrastructure.exception.NotOwnerOfRestaurant;
    import com.pragma.powerup.infrastructure.exception.UserNotExistException;
    import com.pragma.powerup.infrastructure.out.jpa.entity.PlateEntity;
    import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
    import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
    import com.pragma.powerup.infrastructure.out.jpa.repository.IPlateRepository;
    import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
    import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.stereotype.Service;

    import java.util.Optional;

    @Service
    @RequiredArgsConstructor
    public class PlateHandler implements IPlateHandler {

        private final IPlateServicePort plateServicePort;
        private final PlateRequestMapper plateRequestMapper;
        private final PlateUpdateRequestMapper plateUpdateRequestMapper;
        private final PlateResponseMapper plateResponseMapper;
        private final IRestaurantRepository restaurantRepository;
        private final IPlateRepository plateRepository;

        @Override
        public void savePlate(PlateRequestDto plateRequestDto){

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

            plateServicePort.savePlate(plate);
        }

        @Override
        public void updatePlate(PlatePutRequestDto platePutRequestDto) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long userId = ((UserEntity) authentication.getPrincipal()).getIdUser();

            Optional<PlateEntity> plateEntityOptional = plateRepository.findById(platePutRequestDto.getIdPlate());
            if(plateEntityOptional.isEmpty()){
                throw new PlateNotExist("Plate not found");
            }

            PlateEntity plateEntity = plateEntityOptional.get();

            System.out.println(userId);
            System.out.println(plateEntity.getIdRestaurant().getIdUser());

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
        public PlateResponse getPlate(Long idPlate) {
            Plate plate = plateServicePort.getPlate(idPlate);
            return plateResponseMapper.toResponse(plate);
        }
    }
