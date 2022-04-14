package com.mkevin.jwt.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JWTOAuthResrouceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JWTOAuthResrouceApplication.class, args);
	}
	
}
