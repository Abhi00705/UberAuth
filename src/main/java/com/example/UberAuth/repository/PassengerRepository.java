package com.example.UberAuth.repository;

import com.example.UberAuth.models.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findPassengerByEmailId(String emailId);



}
