package com.mkevin.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * 自定义过滤器，添加请求头
 * 自定义Spring Cloud Gateway过滤器工厂需要继承AbstractGatewayFilterFactory类，
 * 重写apply方法的逻辑。命名需要以GatewayFilterFactory结尾，
 * MyAddRequestHeaderGatewayFilterFactory，那么在使用的时候MyAddRequestHeader就是这个过滤器工厂的名称
 */
@Component
public class MyAddRequestHeaderGatewayFilterFactory extends AbstractGatewayFilterFactory<MyAddRequestHeaderGatewayFilterFactory.Config> {

    public MyAddRequestHeaderGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //写法1
        /*return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("MyAddRequestHeaderGatewayFilterFactory.apply is run....");
                // exchange.getRequest().mutate() 目的是转化为装饰类，否则Request为只读的，不能操作
                // header方法用来设置header的值
                ServerHttpRequest request = exchange.getRequest().mutate().header(config.getName(), config.getValue()).build();
                // 将request包裹继续向下传递
                return chain.filter(exchange.mutate().request(request).build());
            }
        };*/

        //写法2
        return (exchange, chain) -> {
            System.out.println("MyAddRequestHeaderGatewayFilterFactory.apply is run....");
            // exchange.getRequest().mutate() 目的是转化为装饰类，否则Request为只读的，不能操作
            // header方法用来设置header的值
            ServerHttpRequest request = exchange.getRequest().mutate().header(config.getName(), config.getValue()).build();
            // 将request包裹继续向下传递
            return chain.filter(exchange.mutate().request(request).build());
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
