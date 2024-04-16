package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.domain.model.OrderPlate;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.domain.model.Plate;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderPlateRequestMapper {

    @Mapping(target = "idPlate", ignore = true)
    OrderPlate toOrderPlate(OrderRequestDto orderRequestDto);

    }
