package com.tech1.tech1test.services;

import com.tech1.tech1test.domain.Color;
import com.tech1.tech1test.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User read(Long id);
    List<User> getUsersWithAgeGreater(Integer age);
    List<User> getUsersByArticleColor(Color color);
    void create(User user);
    User update(User userFromDb, User user);
    void delete(User user);
    List<String> getUserNamesByArticlesCount(Integer count);

}
