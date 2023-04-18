package com.library.library;

import com.library.library.controller.AuthorController;
import com.library.library.controller.UserController;
import com.library.library.model.Author;
import com.library.library.model.User;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthorControllerTests {
    @InjectMocks
    private AuthorController authorController;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthorRepository authorRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void TestCreateAuthor() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Author authorRequest = new Author();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(authorRepository.save(authorRequest)).thenReturn(new Author());
        ResponseEntity<Author> response = authorController.createAuthor(userId, authorRequest);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(userRepository).findById(userId);
        verify(authorRepository).save(authorRequest);
    }
}
