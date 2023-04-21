package com.library.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
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
import java.util.List;

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
    @Test
    public void testGetAllBooks() {
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("Book 1"));
        expectedBooks.add(new Book("Book 2"));
        when(bookRepository.findAll()).thenReturn(expectedBooks);
        List<Book> actualBooks = bookService.getAllBooks();
        assertEquals(expectedBooks.size(), actualBooks.size());
        for (int i = 0; i < expectedBooks.size(); i++) {
            assertEquals(expectedBooks.get(i), actualBooks.get(i));
        }
        verify(bookRepository, times(1)).findAll();
    }
    @Test
    public void testGetAllBooksByAuthorId() {
        Long authorId = 1L;
        Author author = new Author("Author 1");
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book("Book 1"));
        expectedBooks.add(new Book("Book 2"));
        when(authorRepository.existsById(authorId)).thenReturn(true);
        when(bookRepository.findBookByAuthorsId(authorId)).thenReturn(expectedBooks);
        List<Book> actualBooks = bookService.getAllBooksByAuthorId(authorId);
        assertEquals(expectedBooks.size(), actualBooks.size());
        for (int i = 0; i < expectedBooks.size(); i++) {
            assertEquals(expectedBooks.get(i), actualBooks.get(i));
        }
        verify(authorRepository, times(1)).existsById(authorId);
        verify(bookRepository, times(1)).findBookByAuthorsId(authorId);
    }
    @Test
    public void testGetBookById() {
        Long id = 1L;
        Book book = new Book("Test Book");
        book.setId(id);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        Book result = bookService.getBooksById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Test Book", result.getBookName());
    }
    @Test
    public void testGetAllAuthorsByBookId() {
        Long bookId = 1L;
        Book book = new Book("Test Book");
        book.setId(bookId);
        List<Author> expectedAuthors = new ArrayList<>();
        expectedAuthors.add(new Author("Test Author"));
        expectedAuthors.add(new Author("Another Author"));
        when(bookRepository.existsById(bookId)).thenReturn(true);
        when(authorRepository.findAuthorByBooksId(bookId)).thenReturn(expectedAuthors);
        List<Author> actualAuthors = bookService.getAllAuthorsByBookId(bookId);
        assertNotNull(actualAuthors);
        assertEquals(expectedAuthors.size(), actualAuthors.size());
        for (int i = 0; i < expectedAuthors.size(); i++) {
            Author expectedAuthor = expectedAuthors.get(i);
            Author actualAuthor = actualAuthors.get(i);
            assertEquals(expectedAuthor.getId(), actualAuthor.getId());
            assertEquals(expectedAuthor.getFullName(), actualAuthor.getFullName());
        }
    }
}