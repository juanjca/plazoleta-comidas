package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.Plate;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.entity.PlateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlateEntityMapper {

    @Mapping(source = "idCategory.idCategory", target = "idCategory")
    @Mapping(source = "idRestaurant.idUser.idUser", target = "idRestaurant.idUser")
    PlateEntity toEntityPlate(Plate plate);

    @Mapping(source = "idCategory", target = "idCategory.idCategory")
    @Mapping(source = "idRestaurant.idUser", target = "idRestaurant.idUser.idUser")
    Plate toPlate(PlateEntity plateEntity);

}
