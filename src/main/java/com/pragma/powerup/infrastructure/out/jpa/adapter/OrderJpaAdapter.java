package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OrderPlate;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.model.State;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.mapper.OrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderPlateRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public void saveOrder(Orders order) {
        order.setState(State.PENDIENTE);
        orderRepository.save(orderEntityMapper.toOrderEntity(order));
    }
}
