package com.pragma.powerup.application.dto.request;

;
import com.pragma.powerup.domain.model.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStateOrder {

    Long idOrder;
    State state;

}
