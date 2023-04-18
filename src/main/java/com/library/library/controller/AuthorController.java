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
    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorsById(@PathVariable(value = "id") Long id) {
        Optional<Author> a = authorRepository.findById(id);
        if (!a.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(a.get(), HttpStatus.OK);
    }
    @GetMapping("/users/{userId}/authors")
    public ResponseEntity<List<Author>> getAllAuthorsByUserId(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Author> authors = authorRepository.findByUserId(userId);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
    @PutMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") long id, @RequestBody Author authorRequest) {
        Optional<Author> a = authorRepository.findById(id);
        if (!a.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author a1 = a.get();
        a1.setFullName(authorRequest.getFullName());
        return new ResponseEntity<>(authorRepository.save(a1), HttpStatus.OK);
    }
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<HttpStatus> deleteAuthor(@PathVariable("id") long id) {
        authorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/users/{userId}/authors")
    public ResponseEntity<List<Author>> deleteAllAuthorsOfUser(@PathVariable(value = "userId") Long userId) {
        if (!userRepository.existsById(userId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorRepository.deleteByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
