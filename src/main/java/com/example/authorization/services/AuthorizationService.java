package com.example.authorization.services;

import com.example.authorization.dtos.*;
import org.springframework.http.ResponseEntity;

public interface AuthorizationService {

        public SignupResponseDto signup(UserDetailsRequestDto userDetailsRequestDto);

        public LoginResponseDto login(UserDetailsRequestDto userDetailsRequestDto);

        public LogoutResponseDto logout(LogoutRequestDto logoutRequestDto);
}
