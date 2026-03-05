package com.example.UberAuth.DTO;

import com.example.UberAuth.models.Passenger;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerSignupResponseDto {
    Long id;
    private String name;
    private String phoneNumber;
    private String emailId;
    private String password;

    public static PassengerSignupResponseDto from(Passenger p){
        PassengerSignupResponseDto result = PassengerSignupResponseDto.builder()
                .id(p.getId())
                .name(p.getName())
                .phoneNumber(p.getPhoneNumber())
                .emailId(p.getEmailId())
                .password(p.getPassword())
                .build();
        return result;
    }
}
