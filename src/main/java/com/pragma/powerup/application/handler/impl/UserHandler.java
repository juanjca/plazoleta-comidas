package com.pragma.powerup.application.handler.impl;


import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.exception.UserNotLegalAge;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {

        if(!isOlder(userRequestDto.getBirthDate())){
            throw new UserNotLegalAge("You have to be of legal age");
        }
        User user = userRequestMapper.toUser(userRequestDto);
        user.setPassword(hashPassword(user.getPassword()));
        Role role = new Role(1L, "admin", "this is admin the restaurant");
        user.setRole(role);
        userServicePort.saveUser(user);
    }

    public boolean isOlder(LocalDate birthDate) {
        LocalDate newbirthDate = birthDate.plusYears(18);
        return newbirthDate.isBefore(LocalDate.now()) || newbirthDate.equals(LocalDate.now());
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}