package com.pragma.powerup.domain.spi;

import antlr.Token;
import com.pragma.powerup.application.dto.response.TokenResponse;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IUserPersistencePort {

    void saveUser(User user);

}
