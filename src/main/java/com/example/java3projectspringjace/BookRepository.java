package com.example.java3projectspringjace;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Book Data Access Object
 */
public interface BookRepository extends CrudRepository<Book, Integer> {
}
