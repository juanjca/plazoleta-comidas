package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.exception.UserNotOwner;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.infrastructure.exception.UserNotExistException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.application.mapper.RestaurantRequestMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantHandlerTest {

    @Mock
    IRestaurantServicePort restaurantServicePort;

    @Mock
    RestaurantRequestMapper restaurantRequestMapper;

    @Mock
    IUserRepository userRepository;

    @InjectMocks
    RestaurantHandler restaurantHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveRestaurant_UserNotExistException() {

        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();
        restaurantRequestDto.setIdUser(3L);

        when(userRepository.existsById(restaurantRequestDto.getIdUser())).thenReturn(false);

        assertThrows(UserNotExistException.class, () -> restaurantHandler.saveRestaurant(restaurantRequestDto));

        verify(userRepository, times(1)).existsById(restaurantRequestDto.getIdUser());
        verifyNoMoreInteractions(userRepository, restaurantServicePort, restaurantRequestMapper);
    }

    @Test
    void saveRestaurant_UserNotOwner() {

        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();
        restaurantRequestDto.setIdUser(2L);

        when(userRepository.existsById(restaurantRequestDto.getIdUser())).thenReturn(true);

        assertThrows(UserNotOwner.class, () -> restaurantHandler.saveRestaurant(restaurantRequestDto));

        verify(userRepository, times(1)).existsById(restaurantRequestDto.getIdUser());
        verifyNoMoreInteractions(userRepository, restaurantServicePort, restaurantRequestMapper);
    }

    @Test
    void saveRestaurant_Success() {

        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();
        restaurantRequestDto.setIdUser(1L);
        when(userRepository.existsById(restaurantRequestDto.getIdUser())).thenReturn(true);
        when(restaurantRequestMapper.toRestaurant(any())).thenReturn(new Restaurant());

        restaurantHandler.saveRestaurant(restaurantRequestDto);

        verify(userRepository, times(1)).existsById(restaurantRequestDto.getIdUser());
        verify(restaurantRequestMapper, times(1)).toRestaurant(restaurantRequestDto);
        verify(restaurantServicePort, times(1)).saveRestaurant(any(Restaurant.class));
        verifyNoMoreInteractions(userRepository, restaurantServicePort, restaurantRequestMapper);
    }
}
