package com.mkevin.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

//@Configuration
public class InMemoryWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    // 配置认证（用户名密码认证）
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //密码编码器，查看PasswordEncoder接口的实现类，即可知道有多少种
        SCryptPasswordEncoder encoder = new SCryptPasswordEncoder();
        //内存认证
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                //用户名（普通用户)
                .withUser("kevin")
                //密码(要用相同的编码器进行编码）
                .password(encoder.encode("123456"))
                //角色，不可以为null，也不可以以ROLE_开头
                .roles("USER")

                //继续添加用户
                .and()

                //用户名（管理员）
                .withUser("admin")
                .password(encoder.encode("123456"))
                //角色，可以设置多个，普通用户和管理员角色
                .roles("ADMIN")

                .and()
                .withUser("superAdmin")
                .password(encoder.encode("123456"))
                // superadmin用户同时有多个角色
                .roles("USER","ADMIN");
    }

    //配置授权(资源的访问规则)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //允许跨域访问
        http.csrf().disable();
        http.cors().disable();
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
