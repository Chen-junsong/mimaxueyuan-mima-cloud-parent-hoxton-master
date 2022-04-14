package com.mkevin.jwt.server.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JWTOAuthServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(JWTOAuthServerApplication.class, args);
	}
	
}
