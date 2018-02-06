package com.bestaone.springboot.oauth2.aurhserver.dao;

import com.bestaone.springboot.oauth2.aurhserver.domain.Authority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorityDao {

    List<Authority> findByRoleId(Long userId);

}
