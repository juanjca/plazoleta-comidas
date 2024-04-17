package com.pragma.powerup.application.exception;

public class UserNotEmployee extends RuntimeException {
    public UserNotEmployee(String message) {
        super(message);
    }
}
