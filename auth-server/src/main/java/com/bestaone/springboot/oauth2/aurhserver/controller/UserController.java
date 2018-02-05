package com.bestaone.springboot.oauth2.aurhserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model){
        model.addAttribute("username", principal.getName());
        return "user/user";
    }

    @GetMapping({"/detail"})
    public String detail(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        return "/user/detail";
    }

    @PreAuthorize("hasAuthority('/user/me')")
    @GetMapping({"/me"})
    public String me(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        return "/user/me";
    }

    @PreAuthorize("hasAuthority('/user/list')")
    @GetMapping({"/list"})
    public String list(Model model) {
        return "/user/list";
    }

}
