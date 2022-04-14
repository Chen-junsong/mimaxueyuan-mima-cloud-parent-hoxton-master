package com.mkevin.jwt.server.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ResourceController {

    @GetMapping("/user")
    public Principal getProduct(Principal user) {
        return user;
    }

}