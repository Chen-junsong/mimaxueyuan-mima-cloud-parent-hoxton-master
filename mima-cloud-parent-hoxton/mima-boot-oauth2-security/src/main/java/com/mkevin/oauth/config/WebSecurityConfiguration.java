package com.mkevin.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        String pwd = new BCryptPasswordEncoder().encode("123456");//对密码进行加密
        manager.createUser(User.withUsername("user_1").password(pwd).authorities("USER").build());
        manager.createUser(User.withUsername("user_2").password(pwd).authorities("USER").build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.authorizeRequests().antMatchers("/oauth/**","/login","/product/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        //http.formLogin().loginPage("/login").failureUrl("/login?error").permitAll();
        //http.logout().permitAll();
        http.httpBasic();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}