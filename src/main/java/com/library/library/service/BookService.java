package com.library.library.service;

import com.library.library.model.Author;
import com.library.library.model.Book;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
