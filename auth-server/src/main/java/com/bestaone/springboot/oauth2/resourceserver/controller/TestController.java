package com.bestaone.springboot.oauth2.resourceserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TestController {

    @ResponseBody
    @GetMapping("/test/a")
    public String test() {
        return "test!";
    }

}
