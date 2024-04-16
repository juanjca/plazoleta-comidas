package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
import com.pragma.powerup.application.dto.request.PlateRequestDto;
import com.pragma.powerup.application.dto.request.StatePlateRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.PlateResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlateHandler {

    void savePlate(PlateRequestDto plateRequestDto) throws Exception;

    void updatePlate(PlatePutRequestDto platePutRequestDto);

    void changeStatePlate(StatePlateRequestDto statePlateRequestDto);

    PlateResponse getPlate(Long plateNumber);

    List<PlateResponse> getMenuForRestaurant(Long restaurantId);
}