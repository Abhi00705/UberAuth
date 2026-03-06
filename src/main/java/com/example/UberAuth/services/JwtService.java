package com.example.UberAuth.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements CommandLineRunner {
    @Value("${jwt.expiry}")
    private String ExpiryDate;

    @Value("${jwt.secret}")
    private String SecretKey;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("+++++++++++");
        System.out.println("Expiry date: "+ExpiryDate);
        System.out.println("Secret key: "+SecretKey);

    }
}
