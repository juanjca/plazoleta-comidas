package com.pragma.powerup.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.Set;

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

    private String name;

    private Long idCategory;

    private String description;

    @Positive(message = "El precio debe ser un número positivo")
    @Min(value = 1, message = "El precio debe ser mayor que 0")
    private int price;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity idRestaurant;

    private String urlImage;

    private Boolean state;

    @OneToMany(mappedBy = "idPlate")
    Set<PlateEntity> plate;
}
