package com.example.java3projectspringjace;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for Book Data Access Object
 */
public interface BookRepository extends CrudRepository<Book, String> {

    Book findBookByIsbn(String isbn);
}
