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
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        Optional<User> user = userRepository.findById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user1){
        Optional<User> user = userRepository.findById(id);
        User u1 = user.get();
        u1.setName(user1.getName());
        return new ResponseEntity<>(userRepository.save(u1), HttpStatus.OK);
    }
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers(){
        userRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
