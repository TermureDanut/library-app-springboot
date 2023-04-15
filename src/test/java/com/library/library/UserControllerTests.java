package com.library.library;


import com.library.library.controller.UserController;
import com.library.library.model.User;
import com.library.library.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserControllerTests {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateUser() {
        User user = new User("ion");
        when(userRepository.save(any(User.class))).thenReturn(user);
        ResponseEntity<User> response = userController.createUser(user);
        verify(userRepository, times(1)).save(any(User.class));
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() != null;
        assert response.getBody().getName().equals("ion");
    }
}
