package com.mkevin.oauth.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableOAuth2Sso
public class CloudOAuthResrouceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CloudOAuthResrouceApplication.class, args);
	}
	
}
