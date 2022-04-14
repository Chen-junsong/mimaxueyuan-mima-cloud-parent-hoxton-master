package com.mkevin.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * 自定义过滤器添加应答头
 * 通过AbstractGatewayFilterFactory实现
 */
@Component
public class MyAddResponseHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<MyAddResponseHeaderGatewayFilterFactory.Config> {

    public MyAddResponseHeaderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //写法2
        return (exchange, chain) -> {
            System.out.println("MyAddResponseHeaderGatewayFilterFactory.apply is run....");
            exchange.getResponse().getHeaders().set(config.getName(),config.getValue());
            // 继续向上传递
            return chain.filter(exchange);
        };
    }

    @Validated
    public static class Config {

        @NotEmpty
        private String name;
        @NotEmpty
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue(){
            return value;
        }

        public void setValue(String value){
            this.value=value;
        }
    }
}
