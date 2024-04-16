package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.response.TokenResponse;
import com.pragma.powerup.infrastructure.out.jpa.adapter.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Validated
@Component
@RequestMapping("auth")
public class AdminRestController {

    private final AdminService adminService;

    @PostMapping("/login/")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(adminService.login(loginRequestDto));
    }
}
