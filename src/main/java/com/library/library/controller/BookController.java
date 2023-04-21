package com.library.library.controller;

import com.library.library.model.Author;
import com.library.library.model.Book;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorController authorController;
    @Autowired
    private AuthorRepository authorRepository;
    @PostMapping("/authors/{authorId}/books")
    public ResponseEntity<Book> createBook(@PathVariable(value = "authorId") Long authorId, @RequestBody Book bookRequest){
        Book book = bookService.createBook(authorId, bookRequest);
        if (book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}/books")
    public ResponseEntity<List<Book>> getAllBooksByAuthorId(@PathVariable(value = "authorId") Long authorId) {
        List<Book> books = bookService.getAllBooksByAuthorId(authorId);
        if (books.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBooksById(@PathVariable(value = "id") Long id) {
        Book book = bookService.getBooksById(id);
        if (book == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}/authors")
    public ResponseEntity<List<Author>> getAllAuthorsByBookId(@PathVariable(value = "bookId") Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Author> authors = bookService.getAllAuthorsByBookId(bookId);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

}
