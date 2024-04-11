package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
import com.pragma.powerup.application.dto.request.PlateRequestDto;
import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.application.mapper.PlateRequestMapper;
import com.pragma.powerup.application.mapper.PlateResponseMapper;
import com.pragma.powerup.application.mapper.PlateUpdateRequestMapper;
import com.pragma.powerup.domain.api.IPlateServicePort;
import com.pragma.powerup.domain.model.Plate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlateHandlerTest {

    @Mock
    private IPlateServicePort plateServicePort;

    @Mock
    private PlateRequestMapper plateRequestMapper;

    @Mock
    private PlateUpdateRequestMapper plateUpdateRequestMapper;

    @Mock
    private PlateResponseMapper plateResponseMapper;

    @InjectMocks
    private PlateHandler plateHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void savePlate() {
        PlateRequestDto plateRequestDto = new PlateRequestDto();
        Plate plate = new Plate();
        when(plateRequestMapper.toPlate(any(PlateRequestDto.class))).thenReturn(plate);

        plateHandler.savePlate(plateRequestDto);

        verify(plateServicePort, times(1)).savePlate(plate);
    }

    @Test
    void updatePlate() {
        PlatePutRequestDto platePutRequestDto = new PlatePutRequestDto();
        platePutRequestDto.setIdPlate(1L);

        Plate oldPlate = new Plate();
        oldPlate.setName("Old Plate");
        when(plateServicePort.getPlate(any(Long.class))).thenReturn(oldPlate);

        Plate newPlate = new Plate();
        when(plateUpdateRequestMapper.toPlateUpdate(any(PlatePutRequestDto.class))).thenReturn(newPlate);

        plateHandler.updatePlate(platePutRequestDto);

        assertEquals(oldPlate.getName(), newPlate.getName());
        verify(plateServicePort, times(1)).updatePlate(newPlate);
    }

    @Test
    void getPlate() {
        Long idPlate = 1L;
        Plate plate = new Plate();
        when(plateServicePort.getPlate(any(Long.class))).thenReturn(plate);

        PlateResponse plateResponse = new PlateResponse();
        when(plateResponseMapper.toResponse(any(Plate.class))).thenReturn(plateResponse);

        PlateResponse response = plateHandler.getPlate(idPlate);

        assertEquals(plateResponse, response);
    }
}
