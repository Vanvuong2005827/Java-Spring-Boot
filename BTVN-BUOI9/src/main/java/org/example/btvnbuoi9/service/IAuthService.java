package org.example.btvnbuoi9.service;

import org.example.btvnbuoi9.domain.dto.request.UserLoginRequest;
import org.example.btvnbuoi9.domain.dto.request.UserRegisterRequest;
import org.example.btvnbuoi9.domain.dto.response.UserLoginResponse;
import org.example.btvnbuoi9.domain.dto.response.UserRegisterResponse;
import org.example.btvnbuoi9.domain.entities.Token;

public interface IAuthService {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest) throws Exception;
    Token login(UserLoginRequest userLoginRequest);
}
