package com.mkevin.gateway.predicate;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.function.Predicate;

/**
 * 自定义谓词工场类
 * 1、继承AbstractRoutePredicateFactory类，
 * 2、重写apply方法
 * 3、apply方法的参数是自定义的配置类，可以在apply方法中直接获取使用配置参数。
 * 4、类的命名需要以RoutePredicateFactory结尾
 * 本类例子：检查请求参数中的userName是否与配置的数据相同，如果相同则允许访问，否则不允许访问
 */
@Component
public class UserNameCheckRoutePredicateFactory extends AbstractRoutePredicateFactory<UserNameCheckRoutePredicateFactory.Config> {

    public UserNameCheckRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(UserNameCheckRoutePredicateFactory.Config config) {
        // 写法1
        /*return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userName = serverWebExchange.getRequest().getQueryParams().getFirst("userName");
                if(StringUtils.isBlank(userName)){
                    return false;
                }
                //检查请求参数中的userName是否与配置的数据相同，如果相同则允许访问，否则不允许访问
                if(userName.equals(config.getName())){
                    return true;
                }
                return false;
            }
        };*/

        // 写法2
        return serverWebExchange -> {
            String userName = serverWebExchange.getRequest().getQueryParams().getFirst("userName");
            if(StringUtils.isBlank(userName)){
                return false;
            }
            //检查请求参数中的userName是否与配置的数据相同，如果相同则允许访问，否则不允许访问
            if(userName.equals(config.getName())){
                return true;
            }
            return false;
        };
    }

    @Validated
    public static class Config{

        @NotEmpty
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }
    }
}
