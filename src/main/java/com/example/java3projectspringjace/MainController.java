package com.example.java3projectspringjace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Main Controller for the Books Database API
 */

@RestController
@RequestMapping(path="api/v1")
public class MainController {

    public static final String BOOK = "/books";

    public static final String AUTHORS = "/authors";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(path=BOOK)
    public @ResponseBody
    Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping(path = BOOK + "/{isbn}")
    public @ResponseBody
    Book getBookWithId(@PathVariable String isbn){ return bookRepository.findBookByIsbn(isbn);
    }

    @GetMapping(path=AUTHORS)
    public @ResponseBody
    Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

}
