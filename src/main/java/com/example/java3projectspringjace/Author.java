package com.example.java3projectspringjace;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an author class with a unique ID, first name, and last name.
 * This entity class is mapped to the "authors" table in the database.
 * Each author can be associated with multiple books through a Many-To-Many
 * relationship.
 */
@Entity(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;


    @ManyToMany(mappedBy = "authorList")
    @JsonBackReference
    private List<Book> bookList = new ArrayList<>();

    /**
     * Gets the unique identifier for the author.
     * @return the unique identifier as an Integer
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the author.
     * @param id the unique identifier to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the first name of the author.
     * @return the first name of the author as a String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the author.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the author.
     * @return the last name of the author as a String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the author.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns a list of books associated with the author.
     * @return a list of {@link Book} instances associated with the author
     */
    public List<Book> getBookList() {
        return bookList;
    }

    /**
     * Sets the list of books associated with the author.
     * @param bookList the list of books to set
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
