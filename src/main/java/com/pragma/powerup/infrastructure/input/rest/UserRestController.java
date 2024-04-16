package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.handler.impl.OrderHandler;
import com.pragma.powerup.application.handler.impl.OrderPlateHandler;
import com.pragma.powerup.application.handler.impl.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("user")
public class UserRestController {

    private final UserHandler userHandler;
    private final OrderHandler orderHandler;
    private final OrderPlateHandler orderPlateHandler;

    @PostMapping("/")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
            userHandler.saveUser(userRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PostMapping("/employee/")
    public ResponseEntity<String> saveUserEmployee(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUserEmployee(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User Employee created successfully");
    }

    @PostMapping("/client/")
    public ResponseEntity<String> saveUserClient(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUserClient(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client created successfully");
    }

    @PostMapping("/order/")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        orderHandler.saveOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
    }



}