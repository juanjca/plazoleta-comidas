package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.response.TokenResponse;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.out.jpa.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;

    public TokenResponse login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
        UserEntity user = userRepository.findByEmail(loginRequestDto.getEmail());
        String token = jwtService.getToken(user);
        return TokenResponse.builder()
                .token(token)
                .role(user.getIdRole().getName())
                .build();
    }
}
