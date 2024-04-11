package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.PlatePutRequestDto;
import com.pragma.powerup.application.dto.request.PlateRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.PlateResponse;

public interface IPlateHandler {

    void savePlate(PlateRequestDto plateRequestDto);

    void updatePlate(PlatePutRequestDto platePutRequestDto);

    PlateResponse getPlate(Long plateNumber);


}
