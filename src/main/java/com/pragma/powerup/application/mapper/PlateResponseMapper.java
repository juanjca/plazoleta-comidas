package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.PlateResponse;
import com.pragma.powerup.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlateResponseMapper {

    @Mapping(source = "idCategory.idCategory", target = "idCategory")
    @Mapping(source = "idRestaurant.idRestaurant", target = "idRestaurant")
    PlateResponse toResponse(Plate plate);

}
