package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
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
    List<User> getAllUsers(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        return userRepository.findAll();
    }
}
