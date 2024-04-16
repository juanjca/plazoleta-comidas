package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;

public class OrderUseCase implements IOrderServicePort {

    private IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void saveOrder(Orders order) {
        orderPersistencePort.saveOrder(order);
    }
}
