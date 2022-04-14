package com.mkevin.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * 自定义过滤器，添加请求头
 * 通过AbstractNameValueGatewayFilterFactory实现
 *
 * 如果你的配置是Key、Value这种形式的，那么可以不用自己定义配置类，
 * 直接继承AbstractNameValueGatewayFilterFactory类即可。
 * AbstractNameValueGatewayFilterFactory类继承了AbstractGatewayFilterFactory，
 * 定义了一个NameValueConfig配置类，NameValueConfig中有name和value两个字段。
 */
@Component
public class MyAddRequestHeader2GatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {


    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            System.out.println("MyAddRequestHeader2GatewayFilterFactory.apply is run....");
            // exchange.getRequest().mutate() 目的是转化为装饰类，否则Request为只读的，不能操作
            // header方法用来设置header的值
            ServerHttpRequest request = exchange.getRequest().mutate().header(config.getName(), config.getValue()).build();
            // 将request包裹继续向下传递
            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}
