package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.domain.model.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    private LocalDate dateOrder;

    private Long idClient;

    private Long idRestaurant;

    private Long idEmployee;

    private State state;

    @OneToMany(mappedBy = "idOrder")
    Set<OrderPlateEntity> orderPlateEntity;








    private int amount;






}
