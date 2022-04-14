package com.mkevin.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 自定义参数检查过滤器
 * 如果没有携带制定的参数和值，则返回500错误，并在应答头上添加对应的errorMessage
 */
@Component
public class PostLogGatewayFilterFactory extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        //写法2
        return (exchange, chain) -> {
            // 继续向上传递
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                System.out.println("PostLogGatewayFilterFactory is run1...");
                System.out.println("PostLogGatewayFilterFactory is run2...");
                System.out.println("PostLogGatewayFilterFactory is run3...");
            }));
        };
    }

}
