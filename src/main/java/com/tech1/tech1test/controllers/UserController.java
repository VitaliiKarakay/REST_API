package com.tech1.tech1test.controllers;

import com.tech1.tech1test.domain.User;
import com.tech1.tech1test.repository.UserRepo;
import com.tech1.tech1test.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepo userRepo;
    private final UserService userService;

    @Autowired
    public UserController(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") User user) {
        return user;
    }

    @GetMapping("/age/{age}")
    public List<User> getUserByAge(@PathVariable Integer age) {
        List<User> users = userRepo.findAll();
        return users.stream()
                .filter(user -> user.getAge() >= age)
                .collect(Collectors.toList());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        System.out.println(user.getId());
        userService.addUser(user);
        return user;
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") User userFromDb,
                           @RequestBody User user) {
        BeanUtils.copyProperties(user, userFromDb, "id");
        return userRepo.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") User user) {
        userRepo.delete(user);
    }

}
