package com.bestaone.springboot.oauth2.client.service;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    public Account authUser(String userName, String password) {
        if(!"user".equals(userName)) return null;
        Account u = new Account();
        u.setEmail("user@sample.com");
        u.setId(1);
        u.setPassword("123456");
        u.setUserName("user");
        u.setRoleString("ROLE_USER");
        return u;
    }
}