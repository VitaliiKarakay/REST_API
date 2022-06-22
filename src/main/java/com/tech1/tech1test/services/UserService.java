package com.tech1.tech1test.services;

import com.tech1.tech1test.domain.User;
import com.tech1.tech1test.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.getUserById(user.getId());

        if (userFromDb != null) {
            return false;
        }
        userRepo.save(user);
        return true;
    }

}
