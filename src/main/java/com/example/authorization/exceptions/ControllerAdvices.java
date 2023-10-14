package com.example.authorization.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(UserAlreadyExist.class)
    private ResponseEntity<ExceptionDto> handlesUserAlreadyExistException(
            UserAlreadyExist userAlreadyExist
    ){
        return new ResponseEntity<>(
                new ExceptionDto(HttpStatus.NOT_ACCEPTABLE, userAlreadyExist.getMessage()),
                HttpStatus.NOT_ACCEPTABLE
        );
    }


    @ExceptionHandler(LoginFailedException.class)
    private ResponseEntity<ExceptionDto> handleLoginFailedException(
            LoginFailedException loginFailedException
    ){
        return new ResponseEntity<>(
                new ExceptionDto(HttpStatus.BAD_REQUEST,loginFailedException.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
