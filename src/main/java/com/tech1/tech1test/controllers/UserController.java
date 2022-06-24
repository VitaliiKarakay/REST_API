package com.tech1.tech1test.controllers;

import com.tech1.tech1test.domain.Color;
import com.tech1.tech1test.domain.User;
import com.tech1.tech1test.dto.UserDto;
import com.tech1.tech1test.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAll();
        List<UserDto> result = new ArrayList<>();
        for (User user: users) {
            result.add(UserDto.fromUser(user));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);

        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<UserDto>> getUserByAge(@PathVariable Integer age) {
        List<User> users = userService.getUsersWithAgeGreater(age);

        return getListResponseEntity(users);
    }

    private ResponseEntity<List<UserDto>> getListResponseEntity(List<User> users) {
        if (users.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> result = new ArrayList<>();
        for (User user: users) {
            result.add(UserDto.fromUser(user));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/color/{articleColor}")
    public ResponseEntity<List<UserDto>> getUsersByArticleColor(@PathVariable String articleColor) {
        Color color = Color.valueOf(articleColor.toUpperCase());
        List<User> users = userService.getUsersByArticleColor(color);
        return getListResponseEntity(users);
    }

    @GetMapping({"/articles/{count}", "/articles"})
    public ResponseEntity<List<String>> getUserNamesByArticlesCount(@PathVariable(required = false) Integer count) {
        if (count == null) count = 3;
        return new ResponseEntity<> (userService.getUserNamesByArticlesCount(count), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<UserDto> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable("id") User userFromDb,
                       @RequestBody User user) {
        user = userService.update(userFromDb, user);
        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") User user) {
        userService.delete(user);
    }

}
