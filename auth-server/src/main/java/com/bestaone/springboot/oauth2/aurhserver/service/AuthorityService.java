package com.bestaone.springboot.oauth2.aurhserver.service;

import com.bestaone.springboot.oauth2.aurhserver.dao.AuthorityDao;
import com.bestaone.springboot.oauth2.aurhserver.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class AuthorityService {

    @Autowired
    private AuthorityDao dao;

    public List<Authority> findByRoleId(Long userId) {
        return dao.findByRoleId(userId);
    }

}
