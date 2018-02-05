package com.bestaone.springboot.oauth2.aurhserver.service;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public Account authUser(String userName) {
        if(!"user".equals(userName)) return null;
        Account u = new Account();
        u.setEmail("user@sample.com");
        u.setId(1);
        u.setPassword("123");
        u.setUsername("user");
        u.setRoleString("ROLE_USER");
        return u;
    }
}