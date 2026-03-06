package com.example.UberAuth.controller;

import com.example.UberAuth.DTO.PassengerSignupRequestDto;
import com.example.UberAuth.DTO.PassengerSignupResponseDto;
import com.example.UberAuth.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService=authService;

    }

    @GetMapping("/signup/passenger")
    public ResponseEntity<?> signup(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerSignupResponseDto passengerResponseDto = authService.signupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(passengerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signin(){
        return new ResponseEntity<>(10, HttpStatus.CREATED);
    }
}
