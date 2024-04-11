package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatePutRequestDto {

    private Long idPlate;
    private String description;
    private int price;
}
