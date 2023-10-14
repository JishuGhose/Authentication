package com.example.authorization.controllers;

import com.example.authorization.dtos.LoginResponseDto;
import com.example.authorization.dtos.LogoutResponseDto;
import com.example.authorization.dtos.SignupResponseDto;
import com.example.authorization.dtos.UserDetailsRequestDto;
import com.example.authorization.services.AuthorizationService;
import com.example.authorization.services.AuthorizationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthorizationController
{
    private AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService)
    {
        this.authorizationService = authorizationService;
    }

    @PostMapping
    public ResponseEntity<SignupResponseDto> signup(@RequestBody UserDetailsRequestDto userDetailsRequestDto)
    {
        SignupResponseDto signupResponseDto = authorizationService.signup(userDetailsRequestDto);
        return  new ResponseEntity<>(
                signupResponseDto,
                HttpStatus.ACCEPTED
        );

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login( @RequestBody UserDetailsRequestDto userDetailsRequestDto)
    {
        LoginResponseDto loginResponseDto =  authorizationService.login(userDetailsRequestDto);
        return  new ResponseEntity<>(
                loginResponseDto,
                HttpStatus.OK
        );

    }

    @PostMapping("/logout/{token}")
    public ResponseEntity<LogoutResponseDto> logout(@PathVariable("token") String token)
    {
        LogoutResponseDto logoutResponseDto = authorizationService.logout(token);
        return new ResponseEntity<>(
                logoutResponseDto,
                HttpStatus.OK
        );
    }


}
