package com.library.library;

import com.library.library.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class UserTests {
    @Test
    void ConstructorWithoutParamethersTest(){
        User u = new User();
        Assertions.assertEquals("", u.getName());
        Assertions.assertEquals("", u.getEmail());
        Assertions.assertEquals(0, u.getId());
    }
    @Test
    void ConstructorTest() {
        User u = new User("danut", "email");
        Assertions.assertEquals("danut", u.getName());
        Assertions.assertEquals("email", u.getEmail());
    }
    @Test
    void GetIdTest(){
        User u = new User();
        u.setId(100);
        Assertions.assertEquals(100, u.getId());
    }
    @Test
    void SetIdTest(){
        User u = new User();
        u.setId(11);
        Assertions.assertEquals(11, u.getId());
    }
    @Test
    void GetNameTest(){
        User u = new User("danut", "email");
        Assertions.assertEquals("danut", u.getName());
    }
    @Test
    void SetNameTest(){
        User u = new User("maria", "email");
        u.setName("danut");
        Assertions.assertEquals("danut", u.getName());
    }
    @Test
    void GetEmailTest(){
        User u = new User();
        u.setEmail("email");
        Assertions.assertEquals("email", u.getEmail());
    }
    @Test
    void SetEmailTest(){
        User u = new User("maria", "danut");
        u.setEmail("email");
        Assertions.assertEquals("email", u.getEmail());
    }
    @Test
    void ToStringTest(){
        User u = new User("danut", "email");
        String result = "User{" + "id=" + u.getId() + ", name='" + u.getName() + '\'' + ", email='" + u.getEmail() + '\'' + '}';
        Assertions.assertEquals(result, u.toString());
    }
}
