package com.pragma.powerup.application.exception;

public class UserNotOwner extends RuntimeException{
    public UserNotOwner(String msj){
        super(msj);
    }
}
