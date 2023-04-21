package com.library.library.service;

import com.library.library.model.Author;
import com.library.library.model.Book;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    public Book createBook(Long authorId, Book book){
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            long bookId = book.getId();
            if (bookId != 0L) {
                Optional<Book> optionalBook = bookRepository.findById(bookId);
                if (optionalBook.isPresent()) {
                    Book book2 = optionalBook.get();
                    author.addBook(book2);
                    authorRepository.save(author);
                    return bookRepository.save(book2);
                } else {
                    return null;
                }
            } else {
                author.addBook(book);
                authorRepository.save(author);
                return bookRepository.save(book);
            }
        } else {
            return null;
        }
    }
    public List<Book> getAllBooks(){
        List<Book> books = new ArrayList<Book>();
        bookRepository.findAll().forEach(books::add);
        if (books.isEmpty()){
            return null;
        }
        return books;
    }
    public List<Book> getAllBooksByAuthorId(Long authorId){
        if (!authorRepository.existsById(authorId)) {
            return null;
        }
        List<Book> books = bookRepository.findBookByAuthorsId(authorId);
        return books;
    }
    public Book getBooksById(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null){
            return null;
        }
        return book;
    }
    public List<Author> getAllAuthorsByBookId(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            return null;
        }

        List<Author> authors = authorRepository.findAuthorByBooksId(bookId);
        return authors;
    }
}
