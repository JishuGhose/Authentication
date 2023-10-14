package com.example.authorization.services;

import com.example.authorization.dtos.LoginResponseDto;
import com.example.authorization.dtos.LogoutResponseDto;
import com.example.authorization.dtos.SignupResponseDto;
import com.example.authorization.dtos.UserDetailsRequestDto;
import org.springframework.http.ResponseEntity;

public interface AuthorizationService {

        public SignupResponseDto signup(UserDetailsRequestDto userDetailsRequestDto);

        public LoginResponseDto login(UserDetailsRequestDto userDetailsRequestDto);

        public LogoutResponseDto logout(String token);
}
