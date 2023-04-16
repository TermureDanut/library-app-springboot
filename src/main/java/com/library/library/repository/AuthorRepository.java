package com.library.library.repository;

import com.library.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findById(long id);
    List<Author> findByFullName(String fullName);
}
