package com.library.library.service;

import com.library.library.model.User;
import com.library.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User createUser(User u) {
        return userRepository.save(new User(u.getName(), u.getEmail()));
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User updateUser(long id, User user1) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u1 = user.get();
            u1.setName(user1.getName());
            u1.setEmail(user1.getEmail());
            return userRepository.save(u1);
        } else {
            return null;
        }
    }

    public HttpStatus deleteAllUsers() {
        userRepository.deleteAll();
        return HttpStatus.OK;
    }

    public HttpStatus deleteUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }
}