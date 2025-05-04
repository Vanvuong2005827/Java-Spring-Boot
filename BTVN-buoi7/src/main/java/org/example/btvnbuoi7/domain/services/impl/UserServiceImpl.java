package org.example.btvnbuoi7.domain.services.impl;

import org.example.btvnbuoi7.domain.exception.extendedExceptions.ResourceNotFoundException;
import org.example.btvnbuoi7.domain.models.dtos.mapper.UserMapper;
import org.example.btvnbuoi7.domain.models.dtos.request.UserRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.UserResponse;
import org.example.btvnbuoi7.domain.models.entities.User;
import org.example.btvnbuoi7.domain.models.annotation.exists.Exists;
import org.example.btvnbuoi7.domain.repository.UserRepository;
import org.example.btvnbuoi7.domain.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
@Service
@Validated
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;


    @Override
    public List<UserResponse> getAll(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable).getContent();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No user found");
        }
        return userMapper.UsersToResponse(users);
    }

    @Override
    public UserResponse getById(@Exists(entity = User.class, message = "No user found") long id) {
        User user = userRepository.getById(id);
        return userMapper.UserToResponse(user);
    }

    @Override
    public UserResponse create(UserRequest userRequest) {
        User user = userMapper.RequestToUser(userRequest);
        if (userRepository.existsByUsernameAndIdNot(user.getUsername(), 0L)) {
                throw new DataIntegrityViolationException("Username already exists");
        }

        if (userRepository.existsByEmailAndIdNot(user.getEmail(), 0L)) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        user = userRepository.save(user);
        return userMapper.UserToResponse(user);
    }

    @Override
    @Transactional
    public UserResponse update(@Exists(entity = User.class, message = "No user found") long id, UserRequest userRequest) {
        if (userRepository.existsByUsernameAndIdNot(userRequest.getUsername(), id)) {
            throw new DataIntegrityViolationException("Username already exists");
        }

        if (userRepository.existsByEmailAndIdNot(userRequest.getEmail(), id)) {
            throw new DataIntegrityViolationException("Email already exists");
        }
        User user = userRepository.getById(id);
        userMapper.updateUserFromRequest(userRequest, user);
        user = userRepository.save(user);
        return userMapper.UserToResponse(user);
    }

    @Override
    public void deleteById(@Exists(entity = User.class, message = "No user found") long id) {
        userRepository.deleteById(id);
    }
}
