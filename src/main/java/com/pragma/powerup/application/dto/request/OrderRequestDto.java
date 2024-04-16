package com.pragma.powerup.application.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderRequestDto {

    @NotNull
    List<Long> idPlate;

    @NotNull
    LocalDate dateOrder;

    @NotNull
    Long idRestaurant;

    @NotNull
    int amount;

}
