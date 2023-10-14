package com.example.authorization.exceptions;

import lombok.Data;


public class LoginFailedException extends RuntimeException{

    public LoginFailedException(String message)
    {
        super(message);
    }
}
