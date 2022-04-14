package com.mkevin.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ReadMetaDataGatewayFilterFactory extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        //写法2
        return (exchange, chain) -> {
            Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            // get all metadata properties
            Map<String, Object> map = route.getMetadata();
            System.out.println("metadata:"+map);
            return chain.filter(exchange);
        };
    }
}
