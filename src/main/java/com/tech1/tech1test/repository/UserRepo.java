package com.tech1.tech1test.repository;

import com.tech1.tech1test.domain.Color;
import com.tech1.tech1test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("from User u JOIN u.articles a where a.color = :color")
    List<User> getUsersByArticle(Color color);
}
