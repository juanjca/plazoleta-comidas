package com.pragma.powerup.application.dto.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class StatePlateRequestDto {

    @NotNull
    private Long idPlate;

    @NotNull
    private Boolean state;

}
