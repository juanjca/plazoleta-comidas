package com.pragma.powerup.application.handler.impl;


import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.TokenResponse;
import com.pragma.powerup.application.exception.RolUserNotAdmitted;
import com.pragma.powerup.application.exception.UserNotLegalAge;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;
    private final IRoleRepository roleRepository;

    @Override
    public void saveUser(UserRequestDto userRequestDto) {

        if(!isOlder(userRequestDto.getBirthDate())){
            throw new UserNotLegalAge("You have to be of legal age");
        }

        boolean existRole = roleRepository.existsById(userRequestDto.getIdRole());

        if(userRequestDto.getIdRole().equals(1L) || !existRole){
            throw new RolUserNotAdmitted("Role not Admitted");
        }

        User user = userRequestMapper.toUser(userRequestDto);

        user.setPassword(hashPassword(user.getPassword()));
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