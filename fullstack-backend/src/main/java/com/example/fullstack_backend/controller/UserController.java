package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    public UserRepository userRepository;

    @PostMapping("/create")
    public User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
