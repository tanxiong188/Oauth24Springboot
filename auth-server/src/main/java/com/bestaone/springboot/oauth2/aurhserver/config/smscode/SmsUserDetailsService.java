package com.bestaone.springboot.oauth2.aurhserver.config.smscode;

import com.bestaone.springboot.oauth2.aurhserver.domain.Authority;
import com.bestaone.springboot.oauth2.aurhserver.domain.Role;
import com.bestaone.springboot.oauth2.aurhserver.service.AuthorityService;
import com.bestaone.springboot.oauth2.aurhserver.service.RoleService;
import com.bestaone.springboot.oauth2.aurhserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmsUserDetailsService implements UserDetailsService {

    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.bestaone.springboot.oauth2.aurhserver.domain.User user = userService.findByTelNo(username);

        Set<GrantedAuthority> authorities = new HashSet<>();

        List<Role> roles = roleService.findByRoleId(user.getId());
        for (Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }

        List<Authority> authList = authorityService.findByRoleId(user.getId());
        for (Authority auth : authList){
            authorities.add(new SimpleGrantedAuthority(auth.getCode()));
        }

        return new User(user.getUsername(), user.getPassword(),authorities);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}
