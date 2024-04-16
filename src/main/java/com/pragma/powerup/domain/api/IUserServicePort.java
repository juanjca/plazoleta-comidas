package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.model.User;
import org.springframework.data.domain.Page;

public interface IUserServicePort {

    void saveUser(User user);

}
