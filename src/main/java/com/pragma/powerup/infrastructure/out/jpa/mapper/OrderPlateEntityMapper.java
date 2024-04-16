package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrderPlate;
import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderPlateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderPlateEntityMapper {

    @Mapping(source = "idOrder.idClient.idUser", target = "idOrder.idClient")
    @Mapping(source = "idOrder.idRestaurant.idRestaurant", target = "idOrder.idRestaurant")
    @Mapping(source = "idOrder.idEmployee.idUser", target = "idOrder.idEmployee")
    @Mapping(target = "idPlate", ignore = true)
    OrderPlateEntity toOrderPlateEntity(OrderPlate orderPlate);

    @Mapping(source = "idOrder.idClient", target = "idOrder.idClient.idUser")
    @Mapping(source = "idOrder.idRestaurant", target = "idOrder.idRestaurant.idRestaurant")
    @Mapping(source = "idOrder.idEmployee", target = "idOrder.idEmployee.idUser")
    @Mapping(target = "idPlate", ignore = true)
    OrderPlate toOrderPlate(OrderPlateEntity orderPlateEntity);



}
