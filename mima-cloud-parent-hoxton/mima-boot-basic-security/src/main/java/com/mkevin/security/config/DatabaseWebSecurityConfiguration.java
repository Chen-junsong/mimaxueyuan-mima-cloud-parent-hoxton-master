package com.mkevin.security.config;

import com.mkevin.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    // 配置认证（用户名密码认证）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new SCryptPasswordEncoder());
    }

    //配置授权(资源的访问规则)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许跨域访问
        http.csrf().disable();
        //允许基于HttpServletRequest使用
        http.authorizeRequests()
                //对于/oauth/any/** 赋予任何人都可以访问的权限
                .antMatchers("/oauth/any/**").permitAll();
        http.authorizeRequests()
                //对于/oauth/admin/** 只有ADMIN角色可以访问
                .antMatchers(HttpMethod.GET,"/oauth/admin/**")
                .hasRole("ADMIN");
        http.authorizeRequests()
                //对于/oauth/user/** 只有USER角色可以访问
                .antMatchers("/oauth/user/**")
                .hasRole("USER");
        http.authorizeRequests()
                //对于/oauth/both/** 同时具有两个角色才可以访问
                .antMatchers("/oauth/both/**")
                .access("hasRole('ADMIN') and hasRole('USER')");
        //对于没有匹配上的路径, 则需要认证, 不区分角色
        http.authorizeRequests().anyRequest().authenticated();
        // 表单登录认证（通过浏览器访问的时候回跳转到/login登录页），如果不配置，则浏弹出览器自带的登录框
        http.formLogin();
        // httpbasic认证（通过postman访问时）, 如果不配置则自动跳到登录页
        http.httpBasic();
    }

    public static void main(String[] args){
        //测试密码编码器
        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
        String password = encoder.encode("123456");
        System.out.println(password);
    }

}
