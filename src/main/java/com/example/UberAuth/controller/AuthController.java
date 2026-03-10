package com.example.UberAuth.controller;

import com.example.UberAuth.DTO.AuthRequestDTO;
import com.example.UberAuth.DTO.AuthResponseDto;
import com.example.UberAuth.DTO.PassengerSignupRequestDto;
import com.example.UberAuth.DTO.PassengerSignupResponseDto;
import com.example.UberAuth.services.AuthService;
import com.example.UberAuth.services.JwtService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Value("${cookie.expiry}")
    private int cookieExpiry;

    private JwtService jwtService;

//    private Authentication authentication;
    private  AuthService authService;
    public AuthController(AuthService authService, JwtService jwtService){ //, Authentication authentication
//        this.authentication=authentication;
        this.authService=authService;
        this.jwtService = jwtService;


    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<?> signup(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerSignupResponseDto passengerResponseDto = authService.signupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(passengerResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signin(@RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse response, HttpServletRequest request){
        try{
            Authentication authentication = authService.authentication(authRequestDTO);
            System.out.println("-----Singin----------");
            if(authentication.isAuthenticated()){
                Map<String,String> out1 = new HashMap<>();

                Map<String, Object> userDetail = new HashMap<>();
                userDetail.put("emailId", authRequestDTO.getEmailId());
                userDetail.put("password", authRequestDTO.getPassword());
                String jwtToken = jwtService.createToken(userDetail ,authRequestDTO.getEmailId());

                ResponseCookie cookie = ResponseCookie.from("jwtToken", jwtToken)
                        .httpOnly(false)
                        .secure(false)
                        .path("/")
                        .maxAge(cookieExpiry)
                        .build();
                response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
                return new ResponseEntity<>(AuthResponseDto.builder().sucess(true).build(), HttpStatus.OK);
            }
            else{
                return ResponseEntity.ok(AuthResponseDto.builder().sucess(false).build());
            }
        } catch (Exception e) {
         ;
            return new ResponseEntity<>(AuthResponseDto.builder().sucess(false).build(), HttpStatus.OK);
        }

    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(HttpServletRequest request){
        return new ResponseEntity<>("sucessfull", HttpStatus.OK);
    }



}
