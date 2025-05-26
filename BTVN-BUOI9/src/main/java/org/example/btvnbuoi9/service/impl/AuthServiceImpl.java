package org.example.btvnbuoi9.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.btvnbuoi9.constants.AuthMessage;
import org.example.btvnbuoi9.constants.ErrorMessage;
import org.example.btvnbuoi9.domain.dto.request.UserLoginRequest;
import org.example.btvnbuoi9.domain.dto.request.UserRegisterRequest;
import org.example.btvnbuoi9.domain.dto.response.UserLoginResponse;
import org.example.btvnbuoi9.domain.dto.response.UserRegisterResponse;
import org.example.btvnbuoi9.domain.entities.Role;
import org.example.btvnbuoi9.domain.entities.Token;
import org.example.btvnbuoi9.domain.entities.User;
import org.example.btvnbuoi9.domain.mappers.AuthMapper;
import org.example.btvnbuoi9.repository.UserRepository;
import org.example.btvnbuoi9.security.JwtTokenProvider;
import org.example.btvnbuoi9.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthMapper authMapper;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) throws Exception {
        String username = userRegisterRequest.getUsername();
        String password = userRegisterRequest.getPassword();
        String repeatPassword = userRegisterRequest.getRepeatPassword();
        User user = null;

        if (userRepository.findByUsername(username) != null) {
            throw new DataIntegrityViolationException(ErrorMessage.User.ERR_DUPLICATED_USERNAME);
        }

        
        if (password.equals(repeatPassword)) {
            user = authMapper.toUser(userRegisterRequest);
        }

        user.setPassword(encoder.encode(user.getPassword()));

        user.setRole(Role.USER);

        user = userRepository.save(user);

        if (user == null) {
            throw new Exception(AuthMessage.Auth.REGISTER_FAILED);
        }

        return authMapper.toUserRegisterResponse(user);
    }

    @Override
    public Token login(UserLoginRequest userLoginRequest) {
        Authentication auth;
        try {
            auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Wrong username or password", ex);
        }

        String token = jwtTokenProvider.generateToken(auth);
        return new Token(token);
    }
}
