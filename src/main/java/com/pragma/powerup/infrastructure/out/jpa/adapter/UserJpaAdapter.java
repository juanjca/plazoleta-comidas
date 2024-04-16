package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.request.LoginRequestDto;
import com.pragma.powerup.application.dto.response.TokenResponse;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.UserAlreadyExistsException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.out.jpa.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        userRepository.save(userEntityMapper.toUser(user));
    }

}