package com.example.java3projectspringjace;

import jakarta.persistence.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
}
