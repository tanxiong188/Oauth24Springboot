package com.bestaone.springboot.oauth2.aurhserver.service;

import com.bestaone.springboot.oauth2.aurhserver.dao.UserDao;
import com.bestaone.springboot.oauth2.aurhserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class UserService{

    @Autowired
    private UserDao dao;

    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    public User findByTelNo(String telNo) {
        return dao.findByTelNo(telNo);
    }

}
