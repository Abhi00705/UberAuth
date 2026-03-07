package com.example.UberAuth.services;

import com.example.UberAuth.models.Passenger;
import com.example.UberAuth.repository.PassengerRepository;
import com.example.UberAuth.security.AuthPassengerDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailServiceImpl implements UserDetailsService {
    PassengerRepository passengerRepository;
    public UserDetailServiceImpl(PassengerRepository passengerRepository){
        this.passengerRepository=passengerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findPassengerByEmailId(email);
        if(passenger.isPresent()){
            return new AuthPassengerDetails(passenger.get());
        }
        else{
            throw new UsernameNotFoundException("Passenger not found!");
        }

    }
}
