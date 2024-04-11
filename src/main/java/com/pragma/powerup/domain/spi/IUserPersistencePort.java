package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(User user);

}
