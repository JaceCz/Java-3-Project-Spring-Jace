package com.example.java3projectspringjace;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for book entities to abstract and encapsulate all database communication.
 * Extends the {@link CrudRepository} interface provided by Spring Data to include custom
 * retrieval operations.
 */
public interface BookRepository extends CrudRepository<Book, String> {

    Book findBookByIsbn(String isbn);
}
