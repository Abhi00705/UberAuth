package com.example.UberAuth.services;


import com.example.UberAuth.repository.PassengerRepository;
import com.example.UberAuth.security.AuthPassengerDetails;
import com.example.UberProject_EntityService.modles.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService  {
    @Autowired
    private  PassengerRepository passengerRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findByEmailId(email);
        if(passenger.isPresent()){
            return new AuthPassengerDetails(passenger.get());
        }
        else{
            throw new UsernameNotFoundException("Passenger not found!");
        }

    }
}
