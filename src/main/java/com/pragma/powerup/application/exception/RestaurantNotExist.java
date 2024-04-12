package com.pragma.powerup.application.exception;

public class RestaurantNotExist extends RuntimeException {
    public RestaurantNotExist(String message) {
        super(message);
    }
}
