package com.example.java3projectspringjace;

import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for author entities to abstract and encapsulate all database communication.
 * The repository is used to handle CRUD operations on {@link Author} objects.
 */
public interface AuthorRepository extends CrudRepository<Author, Integer> {
}
