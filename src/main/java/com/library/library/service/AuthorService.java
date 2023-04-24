package com.library.library.service;

import com.library.library.model.Author;
import com.library.library.model.User;
import com.library.library.repository.AuthorRepository;
import com.library.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class AuthorService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorRepository authorRepository;
    public Author createAuthor(Long userId, Author authorRequest) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        authorRequest.setUser(user);
        return authorRepository.save(authorRequest);
    }

    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            return null;
        }
        return author.get();
    }

    public List<Author> getAllAuthorsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            return null;
        }
        return authorRepository.findByUserId(userId);
    }

    public Author updateAuthor(Long id, Author authorRequest) {
        Optional<Author> author = authorRepository.findById(id);
        if (!author.isPresent()) {
            return null;
        }
        Author a = author.get();
        a.setFullName(authorRequest.getFullName());
        return authorRepository.save(a);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

    public void deleteAllAuthorsOfUser(Long userId) {
        if (userRepository.existsById(userId)) {
            authorRepository.deleteByUserId(userId);
        }
    }
}
