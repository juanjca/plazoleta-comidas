package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plate")
public class PlateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlate;

    @NotNull
    private String name;

    @NotNull
    private Long idCategory;

    @NotNull
    private String description;

    @NotNull
    @Positive(message = "El precio debe ser un número positivo")
    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private int price;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity idRestaurant;

    @NotNull
    private String urlImage;
}
