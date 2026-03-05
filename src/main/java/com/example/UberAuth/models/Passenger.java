package com.example.UberAuth.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.*;



@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Passenger extends BaseClass {
    @Column(nullable=false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String emailId;

    @Column(nullable = false)
    private String password;


    @OneToMany(mappedBy = "passenger")
    private List<Booking> booking;


}

