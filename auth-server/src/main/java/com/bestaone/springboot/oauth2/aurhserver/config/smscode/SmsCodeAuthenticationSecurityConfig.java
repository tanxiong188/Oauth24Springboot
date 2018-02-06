package com.bestaone.springboot.oauth2.aurhserver.config.smscode;

import com.bestaone.springboot.oauth2.aurhserver.service.AuthorityService;
import com.bestaone.springboot.oauth2.aurhserver.service.RoleService;
import com.bestaone.springboot.oauth2.aurhserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleService;

    @Autowired
    public AuthorityService authorityService;

    @Bean
    public SmsUserDetailsService smsUserDetailsService(){
        SmsUserDetailsService smsUserDetailsService = new SmsUserDetailsService();
        smsUserDetailsService.setUserService(userService);
        smsUserDetailsService.setRoleService(roleService);
        smsUserDetailsService.setAuthorityService(authorityService);
        return smsUserDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //自定义SmsCodeAuthenticationFilter过滤器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter("/mobile/signin/form", "/signin?error2");
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

        //设置自定义SmsCodeAuthenticationProvider的认证器userDetailsService
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(smsUserDetailsService());

        //在UsernamePasswordAuthenticationFilter过滤前执行
        http.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
