package org.example.buoi4.controller;

import lombok.RequiredArgsConstructor;
import org.example.buoi4.model.User;
import org.example.buoi4.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        userService.updateUser(user);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
