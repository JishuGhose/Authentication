package com.example.authorization.dtos;

import lombok.Data;

@Data
public class UserDetailsRequestDto
{
    private String email;
    private String password;

}
