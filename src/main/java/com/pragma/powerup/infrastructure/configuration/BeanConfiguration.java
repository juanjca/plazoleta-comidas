package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.*;
import com.pragma.powerup.domain.spi.*;
import com.pragma.powerup.domain.usecase.*;
import com.pragma.powerup.infrastructure.out.jpa.adapter.*;
import com.pragma.powerup.infrastructure.out.jpa.mapper.*;
import com.pragma.powerup.infrastructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    private final IPlateRepository plateRepository;
    private final PlateEntityMapper plateEntityMapper;

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    private final IOrderPlateRepository orderPlateRepository;
    private final OrderPlateEntityMapper orderPlateEntityMapper;

    @Bean
    public IOrderPlatePersistencePort orderPlatePersistencePort(){
        return new OrderPlateJpaAdapter(orderPlateRepository, orderPlateEntityMapper, plateEntityMapper);
    }

    @Bean
    public IOrderPlateServicePort orderPlateServicePort(){
        return new OrderPlateUseCase(orderPlatePersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort());
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper, userRepository);
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IPlatePersistencePort platePersistencePort(){
        return new PlateJpaAdapter(plateRepository, plateEntityMapper, restaurantRepository);
    }

    @Bean
    public IPlateServicePort plateServicePort(){
        return new PlateUseCase(platePersistencePort());
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

}