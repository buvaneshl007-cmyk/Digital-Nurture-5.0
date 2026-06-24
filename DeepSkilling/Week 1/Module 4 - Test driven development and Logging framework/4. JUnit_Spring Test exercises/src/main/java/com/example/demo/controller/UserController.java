package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Exercise 5: POST endpoint that creates a user.
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.saveUser(user));
    }

    // Exercise 8: a separate endpoint that uses the throwing variant of the
    // service method, so GlobalExceptionHandler has something real to catch
    // when the user doesn't exist. (The plain /{id} endpoint above keeps the
    // original null-returning behavior from the prompt untouched.)
    @GetMapping("/strict/{id}")
    public ResponseEntity<User> getUserStrict(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserByIdOrThrow(id));
    }
}
