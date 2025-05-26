package org.example.btvnbuoi9.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi9.domain.dto.response.UserResponse;
import org.example.btvnbuoi9.domain.entities.User;
import org.example.btvnbuoi9.domain.mappers.UserMapper;
import org.example.btvnbuoi9.repository.UserRepository;
import org.example.btvnbuoi9.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponses(users);
    }
}
