package com.mkevin.zuul.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
//@EnableOAuth2Sso
public class ZuulOauthApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ZuulOauthApplication.class, args);
	}
	
}
