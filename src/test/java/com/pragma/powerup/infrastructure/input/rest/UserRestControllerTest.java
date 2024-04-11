package com.pragma.powerup.infrastructure.input.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.handler.impl.UserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserRestControllerTest {

    private final static String BASE_URL = "/user/";

    MockMvc mockMvc;

    @Mock
    UserHandler userHandler;

    @InjectMocks
    UserRestController userRestController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void saveUser() throws Exception {

        UserRequestDto userRequestDto = buildUserRequestDto();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapToJson(userRequestDto)))
                .andReturn();

        assertEquals(201, result.getResponse().getStatus());

        verify(userHandler, times(1)).saveUser(any(UserRequestDto.class));
    }

    private UserRequestDto buildUserRequestDto() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Juan Jose");
        userRequestDto.setLastname("Cañas");
        userRequestDto.setDni("1005092402");
        userRequestDto.setNumber("3022572323");
        userRequestDto.setBirthDate(LocalDate.of(1990,5,4));
        userRequestDto.setEmail("juanjo2003gg@gmail.com");
        userRequestDto.setPassword("1005092402Jj");
        return userRequestDto;
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
