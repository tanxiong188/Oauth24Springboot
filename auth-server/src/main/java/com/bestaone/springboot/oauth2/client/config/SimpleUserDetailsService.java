package com.bestaone.springboot.oauth2.client.config;

import com.bestaone.springboot.oauth2.client.service.Account;
import com.bestaone.springboot.oauth2.client.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.authUser(username);
        if (account == null) {
            throw new AuthenticationCredentialsNotFoundException("Account is not found.");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(account.getRoleString());
        return new User(username,passwordEncoder.encode(account.getPassword()),authorities);
    }

}
