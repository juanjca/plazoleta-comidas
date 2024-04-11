package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.PlateRequestDto;
import com.pragma.powerup.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlateRequestMapper {

    @Mapping(source = "idCategory", target = "idCategory.idCategory")
    @Mapping(source = "idRestaurant", target = "idRestaurant.idRestaurant")
    Plate toPlate(PlateRequestDto plateRequestDto);
}
