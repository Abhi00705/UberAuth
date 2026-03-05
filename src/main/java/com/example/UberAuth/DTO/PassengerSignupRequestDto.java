package com.example.UberAuth.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerSignupRequestDto {

    private String name;
    private String phoneNumber;
    private String emailId;
    private String password;




}
