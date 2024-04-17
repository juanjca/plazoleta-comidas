package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.Orders;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderPlateEntity;
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

    @Mapping(source = "idEmployee", target = "idEmployee.idUser")
    @Mapping(source = "idClient", target = "idClient.idUser")
    @Mapping(source = "idRestaurant", target = "idRestaurant.idRestaurant")
    Orders toOrder(OrderEntity orderEntity);
}
