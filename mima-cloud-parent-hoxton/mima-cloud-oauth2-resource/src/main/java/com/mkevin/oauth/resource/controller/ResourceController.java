package com.mkevin.oauth.resource.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //获取认证详细信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object credentials = authentication.getCredentials();
        System.out.println(credentials.getClass().getName()+":"+authentication.getCredentials().toString());
        Object principal = authentication.getPrincipal();
        if(principal instanceof User){
            User user = (User)principal;
            System.out.println("principal:"+user);
        }else{
            System.out.println(principal.getClass().getName()+":"+principal);
        }
        Object details = authentication.getDetails();
        System.out.println(details.getClass().getName()+":"+authentication.getDetails().toString());
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //获取认证详细信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object credentials = authentication.getCredentials();
        System.out.println(credentials.getClass().getName()+":"+authentication.getCredentials().toString());
        Object principal = authentication.getPrincipal();
        if(principal instanceof User){
            User user = (User)principal;
            System.out.println("principal:"+user);
        }else{
            System.out.println(principal.getClass().getName()+":"+principal);
        }
        Object details = authentication.getDetails();
        System.out.println(details.getClass().getName()+":"+authentication.getDetails().toString());
        return "order id : " + id;
    }
}