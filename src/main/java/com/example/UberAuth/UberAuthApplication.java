package com.example.UberAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UberAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberAuthApplication.class, args);
	}

}
