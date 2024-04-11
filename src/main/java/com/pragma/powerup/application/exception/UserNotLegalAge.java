package com.pragma.powerup.application.exception;

public class UserNotLegalAge extends RuntimeException{
    public UserNotLegalAge(String message){
        super(message);
    }
}
