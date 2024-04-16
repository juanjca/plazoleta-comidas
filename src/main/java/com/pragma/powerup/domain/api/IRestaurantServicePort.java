package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Restaurant;
import org.springframework.data.domain.Page;

public interface IRestaurantServicePort {

    void saveRestaurant(Restaurant restaurant);

    Page<Restaurant> getAllRestaurantsSortedByName(int page, int size);

}
