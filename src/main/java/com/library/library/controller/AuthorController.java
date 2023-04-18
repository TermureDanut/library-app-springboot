package com.library.library.controller;

import com.library.library.model.Author;
import com.library.library.model.User;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AuthorController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @PostMapping("/users/{userId}/authors")
    public ResponseEntity<Author> createAuthor(@PathVariable(value = "userId") Long userId, @RequestBody Author authorRequest) {
        User user = userRepository.findById(userId)
                .orElse(null);
        Author a;
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            authorRequest.setUser(user);
            a = authorRepository.save(authorRequest);
        }
        return new ResponseEntity<>(a, HttpStatus.CREATED);
    }
}
