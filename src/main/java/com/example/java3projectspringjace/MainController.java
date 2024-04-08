package com.example.java3projectspringjace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main Controller for the Books Database API
 */

@RestController
@RequestMapping(path="api/v1")
public class MainController {

    public static final String BOOK = "/books";

    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path="/books")
    public @ResponseBody
    Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
