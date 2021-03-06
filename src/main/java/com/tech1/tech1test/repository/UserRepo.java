package com.tech1.tech1test.repository;

import com.tech1.tech1test.domain.Color;
import com.tech1.tech1test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {

    @Query("from User u JOIN u.articles a where a.color = :color")
    List<User> getUsersByArticleColor(Color color);

    @Query(nativeQuery = true, value = "select u.username from usr u where (select count(a.id) from article a where a.user_id = u.id) >= :count")
    Set<String> getUserNamesByArticlesCount(int count);

    User findByUsername(String name);
}
