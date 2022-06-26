package com.tech1.tech1test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tech1.tech1test.domain.Article;
import com.tech1.tech1test.domain.User;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

private Long id;
    private String username;
    private Integer age;
    private Set<Article> articles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setAge(age);
        user.setArticles(articles);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getUsername());
        userDto.setAge(user.getAge());
        userDto.setArticles(user.getArticles());

        return userDto;
    }
}
