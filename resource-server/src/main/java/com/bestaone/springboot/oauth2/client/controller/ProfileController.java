package com.bestaone.springboot.oauth2.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @GetMapping("/api/profile")
    public Profile get() {
    	Profile p = new Profile();
    	p.setId("10000");
    	p.setName("bestaone");
    	p.setEmail("117919482@qq.com");
        return p;
    }

}


class Profile {
    private String id;
    private String name;
    private String email;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
