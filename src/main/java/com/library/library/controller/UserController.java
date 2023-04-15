package com.library.library.controller;

import com.library.library.model.User;
import com.library.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User u){
        User user1 = userRepository.save(new User(u.getName()));
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }
}
