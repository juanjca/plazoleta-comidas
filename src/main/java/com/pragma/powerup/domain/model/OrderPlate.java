package com.pragma.powerup.domain.model;

import java.util.List;

public class OrderPlate {

    private Long idOrderPlate;
    private Orders idOrder;
    private List<Plate> idPlate;
    private int amount;

    public OrderPlate(Long idOrderPlate, Orders idOrder, List<Plate> idPlate, int amount) {
        this.idOrderPlate = idOrderPlate;
        this.idOrder = idOrder;
        this.idPlate = idPlate;
        this.amount = amount;
    }

    public OrderPlate() {
    }

    public Long getIdOrderPlate() {
        return idOrderPlate;
    }

    public void setIdOrderPlate(Long idOrderPlate) {
        this.idOrderPlate = idOrderPlate;
    }

    public Orders getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Orders idOrder) {
        this.idOrder = idOrder;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Plate> getIdPlate() {
        return idPlate;
    }

    public void setIdPlate(List<Plate> idPlate) {
        this.idPlate = idPlate;
    }
}
