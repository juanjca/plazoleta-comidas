package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetPlateRequestDto {

    @NotNull
    private Long idPlate;
}
