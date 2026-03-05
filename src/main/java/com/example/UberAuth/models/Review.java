package com.example.UberAuth.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review extends BaseClass{
    private String review;
    private Integer rating;
    @OneToOne
    private Booking booking;
}

