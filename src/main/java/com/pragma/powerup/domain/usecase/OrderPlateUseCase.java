package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderPlateServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderPlate;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPlatePersistencePort;

public class OrderPlateUseCase implements IOrderPlateServicePort {

    private IOrderPlatePersistencePort orderPlatePersistencePort;

    public OrderPlateUseCase(IOrderPlatePersistencePort orderPlatePersistencePort) {
        this.orderPlatePersistencePort = orderPlatePersistencePort;
    }

    @Override
    public void saveOrderPlate(OrderPlate orderPlate) {
        orderPlatePersistencePort.saveOrderPlate(orderPlate);
    }
}
