package com.bestaone.springboot.oauth2.resource.controller;

import com.bestaone.springboot.oauth2.resource.config.ViewData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@PreAuthorize("isAuthenticated()")
@RestController
public class ApiController {

    @GetMapping("/api/me")
    public Principal me(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

    @GetMapping("/api/get")
    public Map<String,Object> get() {
        Map<String,Object> map = new HashMap<>();
        map.put("time", new Date());
        return map;
    }

    @PostMapping("/api/post")
    public ViewData post() {
        return ViewData.ok("POST process has finished.");
    }

    @GetMapping("/api/user")
    public Object get(HttpServletRequest req) {
        SecurityContextImpl sci = (SecurityContextImpl) req.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        if (sci != null) {
            Authentication authentication = sci.getAuthentication();
            if (authentication != null) {
                return authentication.getPrincipal();
            }
        }
        return "none";
    }

    @GetMapping("/api/error")
    public Principal error(@AuthenticationPrincipal Principal principal) {
        int i = 1/0;
        return principal;
    }

    @GetMapping("/api/profile")
    public Map<String, String> profile() {
        Map<String, String> map = new HashMap<>();
        map.put("id","10001");
        map.put("name","bestaone");
        map.put("email","117919482@qq.com");
        return map;
    }

}