package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.mapper.UserRequestMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserHandlerTest {

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private UserRequestMapper userRequestMapper;

    @InjectMocks
    private UserHandler userHandler;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUserTrue() {

        //Preparacion
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Alice");
        userRequestDto.setLastname("Smith");
        userRequestDto.setDni("1005092402");
        userRequestDto.setNumber("3022572323");
        userRequestDto.setBirthDate(LocalDate.of(1990, 5, 15));
        userRequestDto.setEmail("juanjo2003gg@gmail.com");
        userRequestDto.setPassword("245345634");

        //Simula el mapeo del Dto a User
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(new User());

        //Activamos el test
        userHandler.saveUser(userRequestDto);

        //Verificamos la respuesta
        verify(userServicePort, times(1)).saveUser(any(User.class));

    }

    @Test
    void saveUserFalse() {

        //Preparacion
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Alice");
        userRequestDto.setLastname("Smith");
        userRequestDto.setDni("myDni");
        userRequestDto.setNumber("3022572323");
        userRequestDto.setBirthDate(LocalDate.of(1990, 5, 15));
        userRequestDto.setEmail("juanjo2003gg@gmail.com");
        userRequestDto.setPassword("245345634");

        //Simula el mapeo del Dto a User
        when(userRequestMapper.toUser(userRequestDto)).thenThrow(HttpClientErrorException.BadRequest.class);
    }

    @Test
    void saveUserFalse2() {

        //Preparacion
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Alice");
        userRequestDto.setLastname("Smith");
        userRequestDto.setDni("1005092402");
        userRequestDto.setNumber("myNumber");
        userRequestDto.setBirthDate(LocalDate.of(1990, 5, 15));
        userRequestDto.setEmail("juanjo2003gg@gmail.com");
        userRequestDto.setPassword("245345634");

        //Simula el mapeo del Dto a User
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(fail("El número de teléfono debe contener solo numeros"));
    }

    @Test
    void isOlderTest20() {

        LocalDate birthDate = LocalDate.now().minusYears(20);
        boolean result = userHandler.isOlder(birthDate);
        assertTrue(result);
    }

    @Test
    void isOlderTest18() {

        LocalDate birthDate = LocalDate.now().minusYears(18);
        boolean result = userHandler.isOlder(birthDate);
        assertTrue(result);
    }

    @Test
    void isOlderTest16() {

        LocalDate birthDate = LocalDate.now().minusYears(16);
        boolean result = userHandler.isOlder(birthDate);
        assertFalse(result);
    }
}