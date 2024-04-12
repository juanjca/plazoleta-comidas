package com.pragma.powerup.application.dto.request;

import com.pragma.powerup.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Setter
@Getter
public class UserRequestDto {

    @NotNull(message = "El nombre no puede estar vacío")
    private String name;

    @NotNull(message = "El apellido no puede estar vacío")
    private String lastname;

    @NotNull(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "[0-9]+", message = "El DNI debe contener solo numeros")
    private String dni;

    @NotNull(message = "El número de teléfono no puede estar vacío")
    @Size(max = 13, message = "El número de teléfono no debe exceder los 13 caracteres")
    @Pattern(regexp = "\\+?[0-9]+", message = "El número de teléfono debe contener solo numeros")
    private String number;

    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    private LocalDate birthDate;

    @NotNull(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    @NotNull(message = "La contraseña no puede estar vacía")
    private String password;

    @NotNull()
    private Long idRole;

}