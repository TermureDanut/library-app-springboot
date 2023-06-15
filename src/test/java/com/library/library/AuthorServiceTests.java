package com.library.library;

import com.library.library.model.Author;
import com.library.library.model.Book;
import com.library.library.model.User;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.UserRepository;
import com.library.library.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AuthorServiceTests {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorService authorService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void TestCreateAuthor() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Author authorRequest = new Author("ion");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(authorRepository.save(authorRequest)).thenReturn(authorRequest);
        Author result = authorService.createAuthor(userId, authorRequest);
        verify(userRepository, times(1)).findById(userId);
        verify(authorRepository, times(1)).save(authorRequest);
        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals("ion", result.getFullName());
    }
    @Test
    public void testGetAuthorById() {
        Long authorId = 1L;
        Author author = new Author("ion");
        author.setId(authorId);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        Author result = authorService.getAuthorById(authorId);
        verify(authorRepository, times(1)).findById(authorId);
        assertNotNull(result);
        assertEquals(authorId, result.getId());
        assertEquals("ion", result.getFullName());
    }
    @Test
    public void testGetAuthorByFullName() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("John Doe"));
        authors.add(new Author("Jane Smith"));

        when(authorRepository.findAuthorByFullName("John Doe")).thenReturn(authors);
        List<Author> result = authorService.getAuthorByFullName("John Doe");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getFullName());

        verify(authorRepository, times(1)).findAuthorByFullName("John Doe");

        when(authorRepository.findAuthorByFullName("Nonexistent Author")).thenReturn(new ArrayList<>());
        List<Author> emptyResult = authorService.getAuthorByFullName("Nonexistent Author");
        assertNull(emptyResult);
    }

    @Test
    public void testGetAllAuthorsByUserId() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Author author1 = new Author("Author 1");
        author1.setUser(user);
        Author author2 = new Author("Author 2");
        author2.setUser(user);
        when(userRepository.existsById(userId)).thenReturn(true);
        when(authorRepository.findByUserId(userId)).thenReturn(Arrays.asList(author1, author2));
        List<Author> result = authorService.getAllAuthorsByUserId(userId);
        verify(userRepository, times(1)).existsById(userId);
        verify(authorRepository, times(1)).findByUserId(userId);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Author 1", result.get(0).getFullName());
        assertEquals(user, result.get(0).getUser());
        assertEquals("Author 2", result.get(1).getFullName());
        assertEquals(user, result.get(1).getUser());
    }
    @Test
    public void testUpdateAuthor() {
        Long id = 1L;
        Author authorRequest = new Author("ion");
        Author author = new Author("ion");
        author.setId(id);
        when(authorRepository.findById(id)).thenReturn(Optional.of(author));
        when(authorRepository.save(author)).thenReturn(author);
        Author result = authorService.updateAuthor(id, authorRequest);
        verify(authorRepository, times(1)).findById(id);
        verify(authorRepository, times(1)).save(author);
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("ion", result.getFullName());
    }
    @Test
    public void testDeleteAuthor() {
        Long id = 1L;
        authorService.deleteAuthor(id);
        verify(authorRepository, times(1)).deleteById(id);
    }
    @Test
    public void testDeleteAllAuthorsOfUser() {
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        authorService.deleteAllAuthorsOfUser(userId);
        verify(userRepository, times(1)).existsById(userId);
        verify(authorRepository, times(1)).deleteByUserId(userId);
    }
}