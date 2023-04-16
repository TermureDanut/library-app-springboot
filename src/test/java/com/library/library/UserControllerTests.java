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
        User user = new User("ion", "email");
        when(userRepository.save(any(User.class))).thenReturn(user);
        ResponseEntity<User> response = userController.createUser(user);
        verify(userRepository, times(1)).save(any(User.class));
        assert response.getStatusCode() == HttpStatus.CREATED;
        assert response.getBody() != null;
        assert response.getBody().getName().equals("ion");
    }
    @Test
    void testGetAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("ion", "email"));
        users.add(new User("ion", "email"));
        when(userRepository.findAll()).thenReturn(users);
        ResponseEntity<List<User>> response = userController.getAllUsers();
        verify(userRepository, times(1)).findAll();
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().size() == 2;
    }
    @Test
    void testGetUserById() {
        User person = new User("ion", "email");
        person.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(person));
        ResponseEntity<User> response = userController.getUserById(1L);
        verify(userRepository, times(1)).findById(1L);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody() != null;
        assert response.getBody().getId() == 1L;
        assert response.getBody().getName().equals("ion");
    }
    @Test
    public void testUpdateUser() {
        User u = new User();
        u.setId(1L);
        u.setName("ion");
        when(userRepository.findById(1L)).thenReturn(Optional.of(u));
        when(userRepository.save(any(User.class))).thenReturn(u);
        User updatedUser = new User();
        updatedUser.setName("ion1");
        ResponseEntity<User> response = userController.updateUser(1L, updatedUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ion1", response.getBody().getName());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(u);
    }
    @Test
    void testDeleteAllUsers() {
        doNothing().when(userRepository).deleteAll();
        ResponseEntity<HttpStatus> response = userController.deleteAllUsers();
        verify(userRepository, times(1)).deleteAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
    @Test
    void testDeleteUserById() {
        long userId = 1L;
        User user = new User("ion", "email");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(userId);
        ResponseEntity<HttpStatus> responseEntity = userController.deleteUserById(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteUserById_UserNotFound() {
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        ResponseEntity<HttpStatus> responseEntity = userController.deleteUserById(userId);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).deleteById(userId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}
