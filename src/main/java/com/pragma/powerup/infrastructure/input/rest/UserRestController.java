package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.TokenResponse;
import com.pragma.powerup.application.handler.impl.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("user")
public class UserRestController {

    private final UserHandler userHandler;

    @PostMapping("/")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
            userHandler.saveUser(userRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

}