package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {

    @Mapping(source = "idEmployee.idUser", target = "idEmployee")
    @Mapping(source = "idClient.idUser", target = "idClient")
    @Mapping(source = "idRestaurant.idRestaurant", target = "idRestaurant")
    OrderEntity toOrderEntity(Orders order);

}
