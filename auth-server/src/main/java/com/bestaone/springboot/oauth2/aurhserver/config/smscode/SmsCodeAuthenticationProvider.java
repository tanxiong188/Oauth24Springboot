package com.bestaone.springboot.oauth2.aurhserver.config.smscode;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

        //此处写验证短信码
        String smsCode = Cache.get((String) authenticationToken.getPrincipal());
        if(smsCode==null || !smsCode.equals(authentication.getCredentials())){
            throw new InternalAuthenticationServiceException("短信验证码错误");
        }

        //调用自定义的userDetailsService认证
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        //如果user不为空重新构建SmsCodeAuthenticationToken（已认证）
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * 只有Authentication为SmsCodeAuthenticationToken使用此Provider认证
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
