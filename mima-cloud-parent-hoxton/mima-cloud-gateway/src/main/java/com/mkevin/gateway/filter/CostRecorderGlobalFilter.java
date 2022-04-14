package com.mkevin.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器，需要实现GlobalFilter, Ordered接口
 *
 * 例子：输出请求耗时
 */
@Component
public class CostRecorderGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //读取请求参数信息
        String path = exchange.getRequest().getPath().toString();
        System.out.println("[开始]请求路径:"+path);
        long start = System.currentTimeMillis();
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            long end = System.currentTimeMillis();
            System.out.println("[应答]请求路径:"+path+"耗时:"+(end-start)+"ms");
        }));
    }

    @Override
    public int getOrder() {
        return -9999999;
    }
}
