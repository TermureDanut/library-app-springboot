package com.library.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Optional;

import com.library.library.model.Author;
import com.library.library.model.Book;
import com.library.library.repository.UserRepository;
import com.library.library.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.library.library.repository.AuthorRepository;
import com.library.library.repository.BookRepository;
import com.library.library.service.BookService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BookServiceTests {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createBookTest() {
        Long authorId = 1L;
        Author author = new Author();
        author.setId(authorId);
        Book book = new Book();
        book.setId(0L);
        when(authorRepository.findById(anyLong())).thenReturn(Optional.of(author));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(authorRepository.save(author)).thenReturn(author);
        when(bookRepository.save(book)).thenReturn(book);
        Book result = bookService.createBook(authorId, book);
        assertNotNull(result);
        assertEquals(result.getId(), book.getId());
        assertEquals(author.getId(), authorId);
    }
}