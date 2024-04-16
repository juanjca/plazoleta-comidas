package com.pragma.powerup.domain.model;

import com.pragma.powerup.infrastructure.out.jpa.entity.OrderPlateEntity;

import java.time.LocalDate;
import java.util.List;

public class Orders {

    private Long idPedido;
    private LocalDate dateOrder;
    private User idClient;
    private Restaurant idRestaurant;
    private User idEmployee;
    private State state;
    private List<OrderPlateEntity> listPlates;

    public Orders(Long idPedido, LocalDate dateOrder, User idClient, Restaurant idRestaurant, User idEmployee, State state, List<OrderPlateEntity> listPlates) {
        this.idPedido = idPedido;
        this.dateOrder = dateOrder;
        this.idClient = idClient;
        this.idRestaurant = idRestaurant;
        this.idEmployee = idEmployee;
        this.state = state;
        this.listPlates = listPlates;
    }

    public Orders() {
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    public User getIdClient() {
        return idClient;
    }

    public void setIdClient(User idClient) {
        this.idClient = idClient;
    }

    public Restaurant getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Restaurant idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public User getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(User idEmployee) {
        this.idEmployee = idEmployee;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<OrderPlateEntity> getListPlates() {
        return listPlates;
    }

    public void setListPlates(List<OrderPlateEntity> listPlates) {
        this.listPlates = listPlates;
    }
}
