package com.mkevin.nacos.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.mkevin.nacos.config.entity.StudentConfig;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({StudentConfig.class})
public class NacosConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigApplication.class, args);
    }

}
