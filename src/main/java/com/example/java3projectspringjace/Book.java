package com.example.java3projectspringjace;

import jakarta.persistence.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a book class with an ISBN, title, edition number, copyright information,
 * and associated authors.
 * This entity class is mapped to the "titles" table in the database.
 * Each book can have multiple authors, which is managed through a Many-To-Many
 * relationship with the Author class.
 */
@Entity(name = "titles")
public class Book {

    @Id
    private String isbn;
    private String title;

    // @Column(name = "editionNumber")
    private int editionNumber;
    private String copyright;

    @ManyToMany
    @JoinTable(
            name = "author_isbn",
            joinColumns = @JoinColumn(name = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Author> authorList = new ArrayList<>();


    /**
     * Get the ISBN
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the International Standard Book Number (ISBN) of this book.
     * @param isbn the ISBN to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Returns the title of the book.
     * @return the book title as a {@link String}.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the edition number of the book.
     * @return the edition number as an integer.
     */
    public int getEditionNumber() {
        return editionNumber;
    }


    /**
     * Sets the edition number of the book.
     * @param editionNumber the edition number to set
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    /**
     * Returns the copyright information of the book.
     * @return the copyright as a {@link String}.
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Sets the copyright information of the book.
     * @param copyright the copyright to set
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * Returns a list of authors associated with the book.
     * @return the list of {@link Author} instances associated with this book.
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Sets the list of authors associated with the book.
     * This method also manages the bidirectional relationship by ensuring
     * that this book instance is added to each author's list of books.
     * @param authorList the list of authors to set
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
