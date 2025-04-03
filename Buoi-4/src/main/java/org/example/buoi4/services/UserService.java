package org.example.buoi4.services;

import org.example.buoi4.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);
}
