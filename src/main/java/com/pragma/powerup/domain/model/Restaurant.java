package com.pragma.powerup.domain.model;

import javax.validation.constraints.Null;

public class Restaurant {

    @Null
    private Long idRestaurant;
    private String name;
    private String address;
    private User idUser;
    private String numberPhone;
    private String urlLogo;
    private String nit;

    public Restaurant(@Null Long idRestaurant, String name, String address, User idUser, String numberPhone, String urlLogo, String nit) {
        this.idRestaurant = idRestaurant;
        this.name = name;
        this.address = address;
        this.idUser = idUser;
        this.numberPhone = numberPhone;
        this.urlLogo = urlLogo;
        this.nit = nit;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
