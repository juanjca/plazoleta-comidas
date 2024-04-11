package com.pragma.powerup.infrastructure.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
import com.pragma.powerup.application.dto.request.PlateRequestDto;
import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.application.handler.impl.PlateHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PlateRestController.class)
@AutoConfigureMockMvc
class PlateRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlateHandler plateHandler;

    @Test
    void savePlate() throws Exception {
        PlateRequestDto plateRequestDto = new PlateRequestDto();
        plateRequestDto.setName("Test Plate");
        plateRequestDto.setIdCategory(1L);
        plateRequestDto.setDescription("newDescription");
        plateRequestDto.setPrice(35000);
        plateRequestDto.setIdRestaurant(1L);
        plateRequestDto.setUrlImage("newUrl");

        mockMvc.perform(MockMvcRequestBuilders.post("/plate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(plateRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Plate created successfully"));
    }

    @Test
    void updatePlate() throws Exception {
        PlatePutRequestDto platePutRequestDto = new PlatePutRequestDto();
        platePutRequestDto.setIdPlate(1L);

        mockMvc.perform(MockMvcRequestBuilders.put("/plate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(platePutRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void getPlate() throws Exception {
        Long idPlate = 1L;
        PlateResponse plateResponse = new PlateResponse();
        plateResponse.setName("Test Plate");

        when(plateHandler.getPlate(any(Long.class))).thenReturn(plateResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/plate/{number}", idPlate))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Plate"));
    }
}
