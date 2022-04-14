package com.mkevin.security.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth/both")
public class OAuth2BothController {

    @RequestMapping("/testOAuth1/{var}")
    public String testOAuth1(@PathVariable("var") String var){
        return var;
    }

    @RequestMapping("/testOAuth2/{var}")
    public String testOAuth2(@PathVariable("var") String var){
        return var;
    }

}
