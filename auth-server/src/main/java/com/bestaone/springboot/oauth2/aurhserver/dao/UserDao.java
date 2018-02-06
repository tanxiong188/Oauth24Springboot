package com.bestaone.springboot.oauth2.aurhserver.dao;

import com.bestaone.springboot.oauth2.aurhserver.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao{

    @Select("SELECT id, name, username, password, tel_no FROM sys_user where username=#{username}")
    User findByUsername(String username);

    @Select("SELECT id, name, username, password, tel_no FROM sys_user where tel_no=#{telNo}")
    User findByTelNo(String telNo);

}
