package com.tech1.tech1test.controllers;

import com.tech1.tech1test.domain.Color;
import com.tech1.tech1test.domain.User;
import com.tech1.tech1test.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.read(id);
    }

    @GetMapping("/age/{age}")
    public List<User> getUserByAge(@PathVariable Integer age) {
        return userService.getUsersWithAgeGreater(age);
    }

    @GetMapping("/color/{articleColor}")
    public List<User> getUsersByArticleColor (@PathVariable String articleColor) {
        Color color = Color.valueOf(articleColor.toUpperCase());
        return userService.getUsersByArticleColor(color);
    }

    @GetMapping({"/articles/{count}", "/articles"})
    public List<String> getUserNamesByArticlesCount(@PathVariable(required = false) Integer count) {
        if (count == null) count = 3;
        return userService.getUserNamesByArticlesCount(count);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        userService.create(user);
        return user;
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") User userFromDb,
                       @RequestBody User user) {
        user = userService.update(userFromDb, user);
        return user;
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") User user) {
        userService.delete(user);
    }

}
