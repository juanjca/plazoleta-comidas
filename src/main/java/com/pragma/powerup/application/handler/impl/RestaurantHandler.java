package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.exception.UserNotOwner;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.RestaurantRequestMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.infrastructure.exception.UserNotExistException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantRequestMapper restaurantRequestMapper;
    private final IUserRepository userRepository;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {

        Long ownerId = restaurantRequestDto.getIdUser();

        if (!userRepository.existsById(ownerId)) {
            throw new UserNotExistException();
        }

        Optional<UserEntity> userOptional = userRepository.findById(ownerId);
        if (userOptional.isEmpty()) {
            throw new UserNotExistException();
        }

        UserEntity user = userOptional.get();

        if (!isOwner(user)) {
            throw new UserNotOwner("User not Owner");
        }

        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);

        restaurantServicePort.saveRestaurant(restaurant);

    }

    public Page<Restaurant> getAllRestaurantsSortedByName(int page, int size) {
        return restaurantServicePort.getAllRestaurantsSortedByName(page, size);
    }

    private boolean isOwner(UserEntity user){
        return user.getIdRole().getIdRole() == 2L;
    }

}
