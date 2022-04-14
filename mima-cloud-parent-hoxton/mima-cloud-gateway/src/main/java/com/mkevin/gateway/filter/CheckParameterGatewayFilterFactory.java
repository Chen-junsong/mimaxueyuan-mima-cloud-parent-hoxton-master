package com.mkevin.gateway.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 自定义参数检查过滤器
 * 如果没有携带制定的参数和值，则返回500错误，并在应答头上添加对应的errorMessage
 */
@Component
public class CheckParameterGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        //写法2
        return (exchange, chain) -> {
            System.out.println("CheckParameterGatewayFilterFactory.apply is run....");
            String name = config.getName();
            String value = config.getValue();

            String nameV = exchange.getRequest().getQueryParams().getFirst(name);
            if(StringUtils.isBlank(nameV)){
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                exchange.getResponse().getHeaders().set("errorMessage",name+" parameter can not be null");
                return exchange.getResponse().setComplete();
            }

            if(!nameV.equals(value)){
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                exchange.getResponse().getHeaders().set("errorMessage",name+" parameter value need equal "+value);
                return exchange.getResponse().setComplete();
            }

            // 继续向上传递
            return chain.filter(exchange);
        };
    }

}
