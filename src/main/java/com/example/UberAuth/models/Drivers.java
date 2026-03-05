package com.example.UberAuth.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Drivers extends BaseClass{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    private String licenseNumber;

    @OneToMany(mappedBy = "drivers", fetch = FetchType.LAZY)
    @Fetch(value= FetchMode.SUBSELECT)
    private List<Booking> booking;


}

