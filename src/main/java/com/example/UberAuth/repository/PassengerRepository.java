package com.example.UberAuth.repository;


import com.example.UberProject_EntityService.modles.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByEmailId(String emailId);



}
