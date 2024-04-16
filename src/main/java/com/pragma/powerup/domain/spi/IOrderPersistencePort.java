package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Orders;

public interface IOrderPersistencePort {

    void saveOrder(Orders order);


}
