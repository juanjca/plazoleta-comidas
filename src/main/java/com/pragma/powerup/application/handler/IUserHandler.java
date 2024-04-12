package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.TokenResponse;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);



}
