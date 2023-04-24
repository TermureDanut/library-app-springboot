package com.library.library.controller;

import com.library.library.service.AuthorService;
import com.library.library.model.Author;
import com.library.library.model.User;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private UserController userService;

    @PostMapping("/users/{userId}/authors")
    public ResponseEntity<Author> createAuthor(@PathVariable(value = "userId") Long userId, @RequestBody Author authorRequest) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(userId).getBody());
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author author = authorService.createAuthor(userId, authorRequest);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthorsById(@PathVariable(value = "id") Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}/authors")
    public ResponseEntity<List<Author>> getAllAuthorsByUserId(@PathVariable(value = "userId") Long userId) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(userId).getBody());
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Author> authors = authorService.getAllAuthorsByUserId(userId);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") long id, @RequestBody Author authorRequest) {
        Author author = authorService.updateAuthor(id, authorRequest);
        if (author == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable("id") long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/authors")
    public ResponseEntity<Void> deleteAllAuthorsOfUser(@PathVariable(value = "userId") Long userId) {
        Optional<User> user = Optional.ofNullable(userService.getUserById(userId).getBody());
        if (!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAllAuthorsOfUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
