package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.model.State;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderPlateEntity;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OrderResponse {

    private Long idPedido;
    private LocalDate dateOrder;
    private User idClient;
    private Restaurant idRestaurant;
    private User idEmployee;
    private State state;
    private List<OrderPlateEntity> listPlates;

}
