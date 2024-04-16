package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponse;
import com.pragma.powerup.application.handler.impl.RestaurantHandler;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("restaurant")
public class RestaurantRestController {

    private final RestaurantHandler restaurantHandler;

    @PostMapping("/")
    public ResponseEntity<String> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto){
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant created successfully");
    }

    @GetMapping("/list/")
    public ResponseEntity<Page<RestaurantListResponse>> getAllRestaurantsSortedByName(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Page<Restaurant> restaurantPage = restaurantHandler.getAllRestaurantsSortedByName(page, size);
        return ResponseEntity.ok(restaurantPage.map(this::mapToRestaurantListResponse));
    }

    private RestaurantListResponse mapToRestaurantListResponse(Restaurant restaurant) {
        return RestaurantListResponse.builder()
                .name(restaurant.getName())
                .urlLogo(restaurant.getUrlLogo())
                .build();
    }

}
