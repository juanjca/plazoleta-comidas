package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Restaurant;
import org.springframework.data.domain.Page;

public interface IRestaurantPersistencePort {

    void saveRestaurant(Restaurant restaurant);

    Page<Restaurant> getAllRestaurantsSortedByName(int page, int size);

}
