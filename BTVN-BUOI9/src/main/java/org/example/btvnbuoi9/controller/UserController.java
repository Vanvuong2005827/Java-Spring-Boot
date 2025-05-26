package org.example.btvnbuoi9.controller;

import lombok.RequiredArgsConstructor;
import org.example.btvnbuoi9.common.ApiResponse;
import org.example.btvnbuoi9.base.RestApiV1;
import org.example.btvnbuoi9.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RestApiV1
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private UserServiceImpl userService;

    @GetMapping("/getMe")
    public ResponseEntity<?> profile(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(ApiResponse.<UserDetails>builder()
                .status(HttpStatus.OK)
                .data(user)
                .build()
        );
    }
}
