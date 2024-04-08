package com.example.java3projectspringjace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.PrintStream;

/**
 * Simple class to store all book information. The information will not change.
 *
 */
@Entity(name = "titles")
public class Book {

    @Id
    private String isbn;
    private String title;

    // @Column(name = "editionNumber")
    private int editionNumber;
    private String copyright;
    //private List<Author> authorList;

    /**
     * Get the ISBN
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
