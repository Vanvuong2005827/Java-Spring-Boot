package org.example.btvnbuoi9.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi9.repository.UserRepository;
import org.example.btvnbuoi9.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
}
