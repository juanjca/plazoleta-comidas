package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.UserNotExistException;
import com.pragma.powerup.infrastructure.out.jpa.mapper.RestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IUserRepository userRepository;

    @Override
    public void saveRestaurant(Restaurant restaurant) {

//        if(restaurantRepository.findById(restaurant.getIdRestaurant()).isPresent()){
//            throw new UserAlreadyExistsException();
//        }

        boolean existUser = userRepository.findById(restaurant.getIdUser().getIdUser()).isPresent();

        if(!existUser){
            throw new UserNotExistException();
        }
        restaurantRepository.save(restaurantEntityMapper.toRestaurant(restaurant));


    }
}
