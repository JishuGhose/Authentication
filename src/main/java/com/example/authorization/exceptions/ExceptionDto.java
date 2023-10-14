package com.example.authorization.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionDto
{
    private HttpStatus status;
    private String message;


}
