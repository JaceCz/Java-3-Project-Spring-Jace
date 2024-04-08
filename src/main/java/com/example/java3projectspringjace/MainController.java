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

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path=BOOK)
    public @ResponseBody
    Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping(path = BOOK + "/{isbn}")
    public @ResponseBody
    Book getBookWithId(@PathVariable String isbn){
        return bookRepository.findBookByIsbn(isbn);
    }
}
