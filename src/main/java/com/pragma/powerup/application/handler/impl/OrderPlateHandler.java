package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.handler.IOrderPlateHandler;
import com.pragma.powerup.application.mapper.OrderPlateRequestMapper;
import com.pragma.powerup.application.mapper.OrderRequestMapper;
import com.pragma.powerup.domain.api.IOrderPlateServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderPlate;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.model.Plate;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderPlateEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlateEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.OrderPlateEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.mapper.PlateEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderPlateRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IPlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderPlateHandler implements IOrderPlateHandler {

    private final IOrderPlateServicePort orderPlateServicePort;
    private final OrderPlateRequestMapper orderPlateRequestMapper;
    private final OrderPlateEntityMapper orderPlateEntityMapper;
    private final PlateEntityMapper plateEntityMapper;
    private final IOrderPlateRepository orderPlateRepository;
    private final IPlateRepository plateRepository;

    @Override
    public void saveOrderPlate(OrderRequestDto orderRequestDto, Long idPlate) {

        List<Plate> platies = new ArrayList<>();

        Optional<PlateEntity> plateOptional = plateRepository.findById(idPlate);
        Plate plate = plateEntityMapper.toPlate(plateOptional.get());
        platies.add(plate);

        OrderPlate orderPlate = orderPlateRequestMapper.toOrderPlate(orderRequestDto);
        orderPlate.setIdPlate(platies);
        orderPlateServicePort.saveOrderPlate(orderPlate);
    }
}
