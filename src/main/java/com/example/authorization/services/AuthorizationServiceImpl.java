package com.example.authorization.services;

import com.example.authorization.dtos.LoginResponseDto;
import com.example.authorization.dtos.LogoutResponseDto;
import com.example.authorization.dtos.SignupResponseDto;
import com.example.authorization.dtos.UserDetailsRequestDto;
import com.example.authorization.exceptions.LoginFailedException;
import com.example.authorization.exceptions.UserAlreadyExist;
import com.example.authorization.models.Sessions;
import com.example.authorization.models.Users;
import com.example.authorization.repositories.SessionRepo;
import com.example.authorization.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{

    private UserRepo userRepo;
    private SessionRepo sessionRepo;

    @Autowired
    public AuthorizationServiceImpl(UserRepo userRepo,
                                    SessionRepo sessionRepo)
    {
        this.userRepo = userRepo;
        this.sessionRepo = sessionRepo;
    }


    @Override
    public SignupResponseDto signup(UserDetailsRequestDto userDetailsRequestDto) {

        String email = userDetailsRequestDto.getEmail();
        String password = userDetailsRequestDto.getPassword();

         Optional<Users> optionalUsers = userRepo.findByEmail(email);
         if( optionalUsers.isPresent() )
         {
             throw new UserAlreadyExist("User with the same email already exist");
         }

         Users users = new Users();
         users.setEmail(email);
         users.setPassword(password);
         Users savedUser =  userRepo.save(users);

        SignupResponseDto signupResponseDto = new SignupResponseDto();
        signupResponseDto.setEmail(savedUser.getEmail());

         return signupResponseDto;


    }

    @Override
    public LoginResponseDto login(UserDetailsRequestDto userDetailsRequestDto) {



       Optional<Users> isValidUser = userRepo.findByEmailAndAndPassword(userDetailsRequestDto.getEmail(),userDetailsRequestDto.getPassword());
       if( isValidUser.isEmpty() )
       {
           throw new LoginFailedException("User Not Found");
       }

        Users loggedInUser = isValidUser.get();
        Sessions newSession = new Sessions();
        String newToken = createToken(generateRandomNumber());
        newSession.setToken(newToken);
        newSession.setUser(loggedInUser);

        Sessions savedSession = sessionRepo.save(newSession);
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setEmail(savedSession.getUser().getEmail());

        return loginResponseDto;
    }

    @Transactional
    public LogoutResponseDto logout(String token)
    {
         sessionRepo.deleteByToken(token);

        LogoutResponseDto logoutResponseDto = new LogoutResponseDto();
        logoutResponseDto.setMessage("User has been logged out");

        return logoutResponseDto;

    }

    private int generateRandomNumber()
    {
        Random random = new Random();
        int value = random.nextInt(100);
        return value;
    }

    private String createToken(int length)
    {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<length;i++)
        {
            int randomIndex = random.nextInt(characters.length());
            char c = characters.charAt(randomIndex);
            randomString.append(c);
        }

        return randomString.toString();
    }
}
