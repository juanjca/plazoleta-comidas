package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class PlateRequestDto {

    private Long idPlate;

    @NotNull
    private String name;

    @NotNull
    private Long idCategory;

    @NotNull
    private String description;

    @NotNull
    private int price;

    @NotNull
    private Long idRestaurant;

    @NotNull
    private String urlImage;
}
