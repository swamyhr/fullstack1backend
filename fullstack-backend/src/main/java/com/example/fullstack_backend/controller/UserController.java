package com.example.fullstack_backend.controller;

import com.example.fullstack_backend.exception.UserNotFoundException;
import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @PostMapping("/create")
    public User newUser(@RequestBody User user) {
        System.out.println(user);
        return userRepository.save(user);
    }

    @GetMapping("")
    List<User> getAllUsers(HttpServletResponse response) {
        return userRepository.findAll();
    }

    @GetMapping("/getUsr/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/updateUsr/{id}")
    public User editUser(@PathVariable Long id, @RequestBody User updatedUserDetail) {
        return userRepository.findById(id).map((user) -> {
            user.setUserName(updatedUserDetail.getUserName());
            user.setName(updatedUserDetail.getName());
            user.setEmail(updatedUserDetail.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        if(!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "Delete user with  id " + id + "success";
    }
}
