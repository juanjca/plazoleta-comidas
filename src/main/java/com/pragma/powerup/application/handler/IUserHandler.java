package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);

    void saveUserEmployee(UserRequestDto userRequestDto);

    void saveUserClient(UserRequestDto userRequestDto);
}
