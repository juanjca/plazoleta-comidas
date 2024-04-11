package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Role;
import com.pragma.powerup.domain.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;

@Getter
@Setter
public class RestaurantRequestDto {

    private String name;
    private String address;
    private Long idUser;
    private String numberPhone;
    private String urlLogo;
    private String nit;

}
