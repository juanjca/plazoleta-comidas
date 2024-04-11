package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlateResponse {
    private Long idPlate;
    private String name;
    private Long idCategory;
    private String description;
    private int price;
    private Long idRestaurant;
    private String urlImage;
    private boolean active;

}
