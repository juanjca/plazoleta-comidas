package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.exception.UserNotExistException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.RestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IUserRepository userRepository;

    @Override
    public void saveRestaurant(Restaurant restaurant) {

        boolean existUser = userRepository.findById(restaurant.getIdUser().getIdUser()).isPresent();

        if(!existUser){
            throw new UserNotExistException();
        }
        restaurantRepository.save(restaurantEntityMapper.toRestaurant(restaurant));


    }

    @Override
    public Page<Restaurant> getAllRestaurantsSortedByName(int page, int size) {
        Page<RestaurantEntity> restaurantEntityPage = restaurantRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
        return restaurantEntityPage.map(restaurantEntity -> restaurantEntityMapper.toRestaurantEntity(restaurantEntity));
    }

}
