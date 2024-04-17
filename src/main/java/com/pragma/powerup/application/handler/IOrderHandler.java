package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.domain.model.State;

public interface IOrderHandler {

    void saveOrder(OrderRequestDto orderRequestDto);

    void selectOrder(Long idOrder, State state);
}
