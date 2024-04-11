package com.pragma.powerup.infrastructure.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException(){
        super();
    }
}
