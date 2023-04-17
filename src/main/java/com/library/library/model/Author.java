package com.library.library.model;

import jakarta.persistence.*;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Author(){}
    public Author(String fullName){
        this.fullName = fullName;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", fullName='" + fullName + '\'' + '}';
    }
}
