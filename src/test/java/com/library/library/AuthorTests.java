package com.library.library;

import com.library.library.model.Author;
import com.library.library.model.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthorTests {
    @Test
    void TestConstructorWithoutParamethers(){
        Author a = new Author();
        assertNotNull(a);
    }
    @Test
    void TestConstructor(){
        Author a = new Author("name");
        Assertions.assertEquals("name", a.getFullName());
    }
    @Test
    void TestGetID(){
        Author a = new Author("name");
        a.setId(10);
        Assertions.assertEquals(10, a.getId());
    }
    @Test
    void TestSetId(){
        Author a = new Author();
        a.setId(1000);
        Assertions.assertEquals(1000, a.getId());
    }
    @Test
    void TestGetFullName(){
        Author a = new Author("name");
        Assertions.assertEquals("name", a.getFullName());
    }
    @Test
    void TestSetFullName(){
        Author a = new Author();
        a.setFullName("name");
        Assertions.assertEquals("name", a.getFullName());
    }
    @Test
    void TestToString(){
        Author a = new Author("name");
        a.setId(1);
        String expected = "Author{" + "id=" + a.getId() + ", fullName='" + a.getFullName() + '\'' + '}';
        Assertions.assertEquals(expected, a.toString());
    }
}
