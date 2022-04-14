package com.mkevin.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BootBasicSecurityApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BootBasicSecurityApplication.class, args);
	}
	
}
