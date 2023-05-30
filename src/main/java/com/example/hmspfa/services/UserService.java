package com.example.hmspfa.services;

import com.example.hmspfa.entities.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    void deleteUser(Long id);
    User updateUser(User user);
}
