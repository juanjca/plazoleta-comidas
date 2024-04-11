package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;

@Getter
@Setter
public class PlateRequestDto {
    private Long idPlate;
    private String name;
    private Long idCategory;
    private String description;
    private int price;
    private Long idRestaurant;
    private String urlImage;


}
