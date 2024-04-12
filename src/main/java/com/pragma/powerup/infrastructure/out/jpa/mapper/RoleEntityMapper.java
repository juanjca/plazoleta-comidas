package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.infrastructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {

    RoleEntity toRole(Optional<RoleEntity> role);
}
