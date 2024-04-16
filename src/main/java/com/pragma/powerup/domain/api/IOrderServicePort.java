package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Orders;

public interface IOrderServicePort {

    void saveOrder(Orders order);
}
