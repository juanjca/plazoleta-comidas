package com.pragma.powerup.domain.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Plate {

    private Long idPlate;
    private String name;
    private Category idCategory;
    private String description;
    private int price;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant idRestaurant;
    private String urlImage;
    private Boolean active;

    public Plate(Long idPlate, String name, Category idCategory, String description, int price, Restaurant idRestaurant, String urlImage, Boolean active) {
        this.idPlate = idPlate;
        this.name = name;
        this.idCategory = idCategory;
        this.description = description;
        this.price = price;
        this.idRestaurant = idRestaurant;
        this.urlImage = urlImage;
        this.active = true;
    }

    public Long getIdPlate() {
        return idPlate;
    }

    public void setIdPlate(Long idPlate) {
        this.idPlate = idPlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
