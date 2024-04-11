package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RestaurantRequestDto {

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "El nombre del restaurante debe contener al menos una letra, un numero y no puede consistir solo de números")
    private String name;

    @NotNull
    private String address;

    @NotNull
    private Long idUser;

    @NotNull
    @Pattern(regexp = "\\+?[0-9]+")
    @Size(max = 13)
    private String numberPhone;

    @NotNull
    private String urlLogo;

    @NotNull
    @Pattern(regexp = "\\+?[0-9]+")
    private String nit;
}
