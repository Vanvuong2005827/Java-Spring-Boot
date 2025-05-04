package org.example.btvnbuoi7.domain.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.btvnbuoi7.domain.base.RestApiV1;
import org.example.btvnbuoi7.domain.models.dtos.request.UserRequest;
import org.example.btvnbuoi7.domain.models.dtos.response.UserResponse;

import org.example.btvnbuoi7.domain.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
//@RestController
@RequestMapping("/users")
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    @Autowired
    UserServiceImpl userService;
    //--------------------------------getAllUser-----------------------------------------
    //For instance: /?page=0&size=5&sort=username,desc
    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getUsers(
            @PageableDefault(page = 0, size = 100, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        return new ResponseEntity<>(userService.getAll(pageable), HttpStatus.OK);
    }

    //--------------------------------getUserById-----------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    //--------------------------------createUser-----------------------------------------
    @PostMapping("/")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.create(userRequest), HttpStatus.CREATED);
    }

    //--------------------------------updateUser-----------------------------------------

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable long id) {
        return new ResponseEntity<>(userService.update(id, userRequest), HttpStatus.OK);
    }

    //--------------------------------deleteUserById-----------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("delete success", HttpStatus.OK);
    }
}
