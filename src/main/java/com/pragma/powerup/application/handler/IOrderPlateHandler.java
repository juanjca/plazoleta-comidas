package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.domain.model.Orders;

public interface IOrderPlateHandler {

    void saveOrderPlate(OrderRequestDto orderRequestDto, Long idPlate);


}
