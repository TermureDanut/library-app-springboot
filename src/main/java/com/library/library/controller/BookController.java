package com.library.library.controller;

import com.library.library.model.Book;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private AuthorController authorController;
    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<Book> createBook(@PathVariable(value = "authorId") Long authorId, @RequestBody Book bookRequest){
        Book book = bookService.createBook(authorId, bookRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
