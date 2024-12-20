package com.rjaco.controller;

import javax.annotation.Resource;

import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/users")
public class UserAccountController {

    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    @GetMapping("/revoke/{tokenId:.*}")
    public void revokeToken(@PathVariable("tokenId") String token) {
        tokenServices.revokeToken(token);
    }

}