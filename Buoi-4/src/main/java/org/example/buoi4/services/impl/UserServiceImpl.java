package org.example.buoi4.services.impl;

import org.example.buoi4.model.User;
import org.example.buoi4.repository.UserRespository;
import org.example.buoi4.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRespository userRepository;

    public UserServiceImpl(UserRespository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
