package com.library.library.repository;

import com.library.library.model.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByUserId(long id);

    @Transactional
    void deleteByUserId(long id);

    List<Author> findAuthorByBooksId(Long bookId);

    Author findAuthorByFullName(String fullName);
}
