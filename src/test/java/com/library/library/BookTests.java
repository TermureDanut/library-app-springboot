package com.library.library;

import com.library.library.model.Author;
import com.library.library.model.Book;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

public class BookTests {
    @Test
    public void testBookConstructor() {
        Book book = new Book();
        assertNotNull(book);
    }
    @Test
    public void testBookNameConstructor() {
        String bookName = "Test Book";
        Book book = new Book(bookName);
        assertEquals(bookName, book.getBookName());
    }
    @Test
    public void testGetId() {
        Book book = new Book();
        long id = 1;
        book.setId(id);
        assertEquals(id, book.getId());
    }
    @Test
    public void testSetId() {
        Book book = new Book();
        long id = 1;
        book.setId(id);
        assertEquals(id, book.getId());
    }
    @Test
    public void testGetBookName() {
        Book book = new Book();
        String bookName = "Test Book";
        book.setBookName(bookName);
        assertEquals(bookName, book.getBookName());
    }
    @Test
    public void testSetBookName() {
        Book book = new Book();
        String bookName = "Test Book";
        book.setBookName(bookName);
        assertEquals(bookName, book.getBookName());
    }
    @Test
    public void testGetAuthors() {
        Book book = new Book();
        Set<Author> authors = new HashSet<Author>();
        book.setAuthors(authors);
        assertEquals(authors, book.getAuthors());
    }
    @Test
    public void testSetAuthors() {
        Book book = new Book();
        Set<Author> authors = new HashSet<Author>();
        book.setAuthors(authors);
        assertEquals(authors, book.getAuthors());
    }
}