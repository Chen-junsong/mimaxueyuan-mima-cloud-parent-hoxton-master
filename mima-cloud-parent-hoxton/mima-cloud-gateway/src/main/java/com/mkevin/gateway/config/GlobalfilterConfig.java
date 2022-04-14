package com.mkevin.gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器配合
 */
@Configuration
public class GlobalfilterConfig {

    @Bean
    @Order(-1)
    public GlobalFilter globalFilter1(){
      return (exchange, chain)->{
        System.out.println("pre filter globalFilter1...");
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            System.out.println("post filter globalFilter1...");
        }));
      };
    };

    @Bean
    @Order(1)
    public GlobalFilter globalFilter2(){
        return (exchange, chain)->{
            System.out.println("pre filter globalFilter2...");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                System.out.println("post filter globalFilter2...");
            }));
        };
    };

    @Bean
    @Order(2)
    public GlobalFilter globalFilter3(){
        return (exchange, chain)->{
            System.out.println("pre filter globalFilter3...");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                System.out.println("post filter globalFilter3...");
            }));
        };
    };

}