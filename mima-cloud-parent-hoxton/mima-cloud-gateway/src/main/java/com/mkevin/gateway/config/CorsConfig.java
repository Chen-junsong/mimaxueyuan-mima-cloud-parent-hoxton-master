package com.mkevin.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 跨域配置
 *
 * http://www.mkevin.com/123132  ajax -> http://www.mkevin.com/123132/test  X
 * http://abd.mkevin.com/123132  ajax -> http://def.mkevin.com/123132/test  Y
 * http://abd.mkevin.com:8801/123132  ajax -> http://abd.mkevin.com:9901/123132/test  Y
 * http://abd.mkevin.com:8801/123132  ajax -> https://abd.mkevin.com:8801/123132/test  Y
 */
@Configuration
public class CorsConfig {
    private static final String MAX_AGE = "18000L";
    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            System.out.println("corsFilter... run");
            ServerHttpRequest request = ctx.getRequest();
            if (!CorsUtils.isCorsRequest(request)) {
                return chain.filter(ctx);
            }
            HttpHeaders requestHeaders = request.getHeaders();
            ServerHttpResponse response = ctx.getResponse();
            HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
            HttpHeaders headers = response.getHeaders();
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
            headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
            if (requestMethod != null) {
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
            }
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
            return chain.filter(ctx);
        };
    }
}
