package org.example.btvnbuoi9.controller;

import lombok.RequiredArgsConstructor;
import org.example.btvnbuoi9.base.RestApiV1;
import org.example.btvnbuoi9.common.ApiResponse;
import org.example.btvnbuoi9.domain.dto.response.UserResponse;
import org.example.btvnbuoi9.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RestApiV1
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    AdminServiceImpl adminService;

    @GetMapping("/users")
    public ResponseEntity<?> users() {
        return ResponseEntity.ok(ApiResponse.<List<UserResponse>>builder()
                .status(HttpStatus.OK)
                .message("success")
                .data(adminService.getUsers())
                .build()
        );
    }
}
