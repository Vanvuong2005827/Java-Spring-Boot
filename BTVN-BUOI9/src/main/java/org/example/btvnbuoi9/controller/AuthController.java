package org.example.btvnbuoi9.controller;

import lombok.RequiredArgsConstructor;
import org.example.btvnbuoi9.common.ApiResponse;
import org.example.btvnbuoi9.base.RestApiV1;
import org.example.btvnbuoi9.constants.AuthMessage;
import org.example.btvnbuoi9.domain.dto.request.UserLoginRequest;
import org.example.btvnbuoi9.domain.dto.request.UserRegisterRequest;
import org.example.btvnbuoi9.domain.dto.response.UserRegisterResponse;
import org.example.btvnbuoi9.domain.entities.Token;
import org.example.btvnbuoi9.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RestApiV1
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        return ResponseEntity.ok(ApiResponse.<UserRegisterResponse>builder()
                .status(HttpStatus.OK)
                .message(AuthMessage.Auth.REGISTER_SUCCESS)
                .data(authService.register(userRegisterRequest))
                .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) throws Exception {
        return ResponseEntity.ok(ApiResponse.<Token>builder()
                .status(HttpStatus.OK)
                .message(AuthMessage.Auth.LOGIN_SUCCESS)
                .data(authService.login(userLoginRequest))
                .build()
        );
    }

}
