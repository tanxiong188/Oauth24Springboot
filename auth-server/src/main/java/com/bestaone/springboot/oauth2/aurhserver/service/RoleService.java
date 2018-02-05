package com.bestaone.springboot.oauth2.aurhserver.service;

import com.bestaone.springboot.oauth2.aurhserver.dao.RoleDao;
import com.bestaone.springboot.oauth2.aurhserver.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class RoleService {

    @Autowired
    private RoleDao dao;

    public List<Role> findByRoleId(Long userId) {
        return dao.findByRoleId(userId);
    }

}
