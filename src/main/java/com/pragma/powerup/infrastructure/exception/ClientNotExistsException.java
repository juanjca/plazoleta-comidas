package com.pragma.powerup.infrastructure.exception;

public class ClientNotExistsException extends RuntimeException{
    public ClientNotExistsException(String text){
        super(text);
    }



}
