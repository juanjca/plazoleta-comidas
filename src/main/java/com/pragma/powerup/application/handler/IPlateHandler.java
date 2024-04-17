package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.*;
import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.domain.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlateHandler {

    void savePlate(PlateRequestDto plateRequestDto) throws Exception;

    void updatePlate(PlatePutRequestDto platePutRequestDto);

    void changeStatePlate(StatePlateRequestDto statePlateRequestDto);

    PlateResponse getPlate(Long plateNumber);

    List<PlateResponse> getMenuForRestaurant(Long restaurantId);

    Page<List<Orders>> getOrderByState(ChangeStateOrder changeStateOrder, int page, int size);
}