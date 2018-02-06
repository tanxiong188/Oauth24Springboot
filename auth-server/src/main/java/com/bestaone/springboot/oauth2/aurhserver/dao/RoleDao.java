package com.bestaone.springboot.oauth2.aurhserver.dao;

import com.bestaone.springboot.oauth2.aurhserver.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {

    List<Role> findByRoleId(Long userId);

}
