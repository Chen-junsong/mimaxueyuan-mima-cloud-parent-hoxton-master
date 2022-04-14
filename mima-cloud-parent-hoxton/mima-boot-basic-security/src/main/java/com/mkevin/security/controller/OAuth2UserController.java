package com.mkevin.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oauth/user")
public class OAuth2UserController {

    @Autowired
    JdbcTemplate jdbcTempalte;

    @RequestMapping("/testOAuth1/{var}")
    public String testOAuth1(@PathVariable("var") String var){

        List<Map<String, Object>> list = jdbcTempalte.queryForList("select * from auth_user");
        System.out.println(list);

        return var;
    }

    @RequestMapping("/testOAuth2/{var}")
    public String testOAuth2(@PathVariable("var") String var){
        return var;
    }

}
