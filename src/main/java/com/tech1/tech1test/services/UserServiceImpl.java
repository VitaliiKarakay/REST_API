package com.tech1.tech1test.services;

import com.tech1.tech1test.domain.Color;
import com.tech1.tech1test.domain.User;
import com.tech1.tech1test.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User findById(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        User user = new User(0L, "User not exist", 0);
        if (optionalUser.isPresent()) {
           user = optionalUser.get();
        }
        return user;

    }

    @Override
    public User findByName(String name) {
        Optional<User> optionalUser = Optional.ofNullable(userRepo.findByName(name));
        User user = new User();
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    public List<User> getUsersWithAgeGreater(Integer age) {
        List<User> allUsers = userRepo.findAll();
        for (User user : allUsers) {
            user.getArticles().clear();
        }
        return allUsers.stream().filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public List<User> getUsersByArticleColor(Color color) {
        return userRepo.getUsersByArticleColor(color).stream().distinct().collect(Collectors.toList());
    }

    public void create(User user) {
        userRepo.save(user);
    }

    public User update(User userFromDb, User user) {

        userFromDb.setName(user.getName());
        userFromDb.setAge(user.getAge());
        userFromDb.setArticles(userFromDb.getArticles());
        return userRepo.save(userFromDb);
    }

    public void delete(User user) {
        Optional<User> optionalUser = userRepo.findById(user.getId());
        if (optionalUser.isPresent()) {
            userRepo.delete(user);
        }
    }

    public List<String> getUserNamesByArticlesCount(Integer count) {
        return userRepo.getUserNamesByArticlesCount(count).stream().distinct().collect(Collectors.toList());
    }
}
