package com.example.hmspfa.services;

import com.example.hmspfa.entities.User;
import com.example.hmspfa.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id) throws UserNotFoundException;
    void deleteUser(Long id) throws  UserNotFoundException;
    User updateUser(User user) throws UserNotFoundException;
    List<User> getAllUsers();
}
