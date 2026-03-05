package com.example.UberAuth.services;

import com.example.UberAuth.DTO.PassengerSignupRequestDto;
import com.example.UberAuth.DTO.PassengerSignupResponseDto;
import com.example.UberAuth.models.Passenger;
import com.example.UberAuth.repository.PassengerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private PassengerRepository passengerRepository;
    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.passengerRepository=passengerRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
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
}
