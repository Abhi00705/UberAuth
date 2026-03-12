package com.example.UberAuth.services;

import com.example.UberAuth.DTO.AuthRequestDTO;
import com.example.UberAuth.DTO.PassengerSignupRequestDto;
import com.example.UberAuth.DTO.PassengerSignupResponseDto;
import com.example.UberAuth.repository.PassengerRepository;
import com.example.UberProject_EntityService.modles.Passenger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private PassengerRepository passengerRepository;

    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager){
        this.passengerRepository=passengerRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.authenticationManager=authenticationManager;
    }

    public PassengerSignupResponseDto signupPassenger(PassengerSignupRequestDto requestDto){
        Passenger passenger = Passenger.builder()
                .name(requestDto.getName())
                .emailId(requestDto.getEmailId())
                .phoneNumber(requestDto.getPhoneNumber())
                .password(bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .build();

        Passenger newPassenger = passengerRepository.save(passenger);
        return PassengerSignupResponseDto.from(newPassenger);

    }
    public Authentication authentication(AuthRequestDTO authRequestDTO){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequestDTO.getEmailId(),
                authRequestDTO.getPassword()
        ));

        return authentication;
    }
}
