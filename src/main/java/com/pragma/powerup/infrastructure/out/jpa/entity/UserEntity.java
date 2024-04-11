package com.pragma.powerup.infrastructure.out.jpa.entity;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String name;
    private String lastname;
    private String dni;
    private String number;
    private LocalDate birthDate;
    private String email;
    private String password;
    private String role;
}
