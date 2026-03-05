package com.example.UberAuth.models;


import com.example.UberAuth.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends BaseClass{

    @OneToOne(cascade = CascadeType.ALL)
    private Review review;

    private Long distance;

    private BookingStatus bookingStatus;


//    @Temporal(TemporalType.TIMESTAMP)// not need to mention because hibernate will automatically detecte it.
    private LocalDateTime startTime;

//    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endTime;

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Drivers drivers;

}

