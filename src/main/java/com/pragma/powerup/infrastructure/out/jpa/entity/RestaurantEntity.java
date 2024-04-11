package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurant;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "El nombre del restaurante debe contener al menos una letra y no puede consistir solo de números")
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
