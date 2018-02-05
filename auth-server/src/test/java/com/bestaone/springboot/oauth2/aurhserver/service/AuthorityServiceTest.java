package com.bestaone.springboot.oauth2.aurhserver.service;

import com.bestaone.springboot.oauth2.aurhserver.Application;
import com.bestaone.springboot.oauth2.aurhserver.domain.Authority;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AuthorityServiceTest {

    @Autowired
    public AuthorityService service;

    @Test
    public void findByRoleIdTest() {
        List<Authority> roles = service.findByRoleId(2L);
        Assert.assertNotNull(roles);
    }

}
