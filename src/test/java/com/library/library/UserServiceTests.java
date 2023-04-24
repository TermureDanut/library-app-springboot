package com.library.library;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import com.library.library.model.User;
import com.library.library.repository.UserRepository;
import com.library.library.service.UserService;

public class UserServiceTests {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCreateUser() {
        User user = new User("ion", "ion");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.createUser(user);
        assertEquals(user, createdUser);
    }
    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("ion", "ion"));
        userList.add(new User("ion", "ion"));
        when(userRepository.findAll()).thenReturn(userList);
        List<User> retrievedUsers = userService.getAllUsers();
        assertEquals(userList, retrievedUsers);
    }
    @Test
    public void testGetUserById() {
        long userId = 1L;
        User user = new User("ion", "ion");
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User retrievedUser = userService.getUserById(userId);
        assertEquals(user, retrievedUser);
    }
    @Test
    public void testUpdateUser() {
        long userId = 1L;
        User user = new User("ion", "ion");
        user.setId(userId);
        User updatedUser = new User("ion", "ion");
        updatedUser.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(updatedUser);
        User returnedUser = userService.updateUser(userId, updatedUser);
        assertEquals(updatedUser, returnedUser);
    }
    @Test
    public void testDeleteAllUsers() {
        HttpStatus result = userService.deleteAllUsers();
        assertEquals(HttpStatus.OK, result);
        verify(userRepository, times(1)).deleteAll();
    }
    @Test
    public void testDeleteUserById() {
        long userId = 1L;
        User user = new User("ion", "ion");
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        HttpStatus result = userService.deleteUserById(userId);
        assertEquals(HttpStatus.OK, result);
        verify(userRepository, times(1)).deleteById(userId);
    }
}