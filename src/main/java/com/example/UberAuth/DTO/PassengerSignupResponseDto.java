package com.example.UberAuth.DTO;

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
}
