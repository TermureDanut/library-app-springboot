package com.library.library.controller;

import com.library.library.model.User;
import com.library.library.service.UserService;
import com.library.library.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.FOUND);
    }
    @GetMapping("/users/getByName/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name){
        List<User> users = userService.getUserByName(name);
        if (users == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }
    @GetMapping("/users/getByEmail/{email}")
    public ResponseEntity<List<User>> getUserByEmail(@PathVariable("email") String email){
        List<User> users = userService.getUserByEmail(email);
        if (users == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
        Optional<User> userToUpdate = Optional.ofNullable(userService.getUserById(id));
        if (!userToUpdate.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User updatedUser = userService.updateUser(userToUpdate.get().getId(), user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers(){
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable("id") long id){
        Optional<User> user = Optional.ofNullable(userService.getUserById(id));
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}