package com.tech1.tech1test.repository;

import com.tech1.tech1test.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User getUserById(Long id);

    List<User> getUsersByAge(Integer age);
}
