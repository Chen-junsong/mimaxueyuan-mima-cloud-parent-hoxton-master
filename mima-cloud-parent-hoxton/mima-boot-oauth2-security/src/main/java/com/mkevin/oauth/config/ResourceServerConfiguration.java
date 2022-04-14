package com.mkevin.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 配置资源服务器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //配置order访问控制，必须认证过后才可以访问
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/oauth/**","/product/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
    }
}