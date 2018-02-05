package com.bestaone.springboot.oauth2.client;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.UnsupportedEncodingException;

public class Test {

    public static void main(String[] args) {
        System.out.println(generate("client", "123456"));
    }

    private static String generate(String clientId, String clientSecret) {
        String creds = String.format("%s:%s", new Object[] { clientId, clientSecret });
        try {
            return "Basic " + new String(Base64.encode(creds.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Could not convert String");
        }
    }

}