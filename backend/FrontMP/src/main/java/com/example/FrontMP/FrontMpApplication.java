package com.example.FrontMP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication

@EnableCircuitBreaker
public class FrontMpApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrontMpApplication.class, args);
	}

}
