package com.pragma.powerup.domain.model;
import lombok.Data;

import java.time.LocalDate;

public class User {

    private Long idUser;
    private String name;
    private String lastname;
    private String dni;
    private String number;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Role role;

    public User(Long idUser, String name, String lastname, String dni, String number, LocalDate birthDate, String email, String password, Role role) {
        this.idUser = idUser;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.number = number;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getNumber() {
        return number;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}


