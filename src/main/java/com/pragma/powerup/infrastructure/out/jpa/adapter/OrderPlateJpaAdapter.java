package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OrderPlate;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.model.Plate;
import com.pragma.powerup.domain.model.State;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IOrderPlatePersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderPlateEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlateEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.OrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.OrderPlateEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.PlateEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderPlateRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderPlateJpaAdapter implements IOrderPlatePersistencePort {

    private final IOrderPlateRepository orderPlateRepository;
    private final OrderPlateEntityMapper orderPlateEntityMapper;
    private final PlateEntityMapper plateEntityMapper;


    @Override
    public void saveOrderPlate(OrderPlate orderPlate) {

        List<Plate> listPlate = orderPlate.getIdPlate();

        OrderPlateEntity orderPlateEntity = orderPlateEntityMapper.toOrderPlateEntity(orderPlate);
        for(Plate plate : listPlate){
            PlateEntity plateEntityMap = plateEntityMapper.toEntityPlate(plate);
            orderPlateEntity.setIdPlate(plateEntityMap);
        }

        orderPlateRepository.save(orderPlateEntity);
    }
}
