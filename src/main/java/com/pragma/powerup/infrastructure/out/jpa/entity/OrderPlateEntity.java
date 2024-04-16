package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapping;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderPlate")
public class OrderPlateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderPlate;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private OrderEntity idOrder;

    @ManyToOne
    @JoinColumn(name = "idPlate")
    private PlateEntity idPlate;

    private int amount;
}
