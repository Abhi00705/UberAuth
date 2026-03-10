package com.example.UberAuth.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {
    private String emailId;
    private String password;
}
