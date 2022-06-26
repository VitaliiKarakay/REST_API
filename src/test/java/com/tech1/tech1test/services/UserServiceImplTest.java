package com.tech1.tech1test.services;

import com.tech1.tech1test.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    public UserServiceImplTest() {
    }

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testCreate() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setAge(10);
        userService.create(user);

        Assert.assertNotNull(user.getStatus());
        Assert.assertEquals(1, user.getRoles().size());
        Assert.assertNotEquals("testPassword", user.getPassword());
    }
}