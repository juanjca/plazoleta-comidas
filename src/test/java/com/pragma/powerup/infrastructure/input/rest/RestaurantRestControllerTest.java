//package com.pragma.powerup.infrastructure.input.rest;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
//import com.pragma.powerup.application.dto.request.UserRequestDto;
//import com.pragma.powerup.application.handler.impl.RestaurantHandler;
//import com.pragma.powerup.application.handler.impl.UserHandler;
//import com.pragma.powerup.domain.model.Restaurant;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//class RestaurantRestControllerTest {
//
//    private final static String BASE_URL = "/restaurant/";
//
//    MockMvc mockMvc;
//
//    @Mock
//    RestaurantHandler restaurantHandler;
//
//    @InjectMocks
//    RestaurantRestController restaurantRestController;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(restaurantRestController).build();
//        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//    }
//
//    @Test
//    void saveRestaurant() throws Exception {
//
//        RestaurantRequestDto restaurantRequestDto = buildRestaurantRequestDto();
//
//        String jsonRequest = mapToJson(restaurantRequestDto);
//
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andReturn();
//
//        assertEquals(201, result.getResponse().getStatus());
//
//        verify(restaurantHandler, times(1)).saveRestaurant(any(RestaurantRequestDto.class));
//    }
//
//    private RestaurantRequestDto buildRestaurantRequestDto() {
//        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();
//        restaurantRequestDto.setName("Ponporino");
//        restaurantRequestDto.setAddress("ponporino@gmail.com");
//        restaurantRequestDto.setIdUser(1L);
//        restaurantRequestDto.setNumberPhone("3105223625");
//        restaurantRequestDto.setUrlLogo("httpadjaodj");
//        restaurantRequestDto.setNit("1009800892");
//        return restaurantRequestDto;
//    }
//
//    private String mapToJson(Object object) throws JsonProcessingException {
//        return objectMapper.writeValueAsString(object);
//    }
//}