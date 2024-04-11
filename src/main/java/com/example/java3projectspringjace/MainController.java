package com.example.java3projectspringjace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for handling all web requests related to Books and Authors.
 * Maps all operations under the "api/v1" base URL.
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

    /**
     * Retrieves all books available in the repository.
     * @return an iterable collection of books.
     */
    @GetMapping(path=BOOK)
    public @ResponseBody
    Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its ISBN.
     * @param isbn The ISBN of the book to retrieve.
     * @return the book if found, or {@code null} if not found.
     */
    @GetMapping(path = BOOK + "/{isbn}")
    public @ResponseBody
    Book getBookWithId(@PathVariable String isbn){ return bookRepository.findBookByIsbn(isbn);
    }

    /**
     * Adds a new book to the repository with the provided details and associates it with an author.
     * @param isbn The ISBN of the new book.
     * @param title The title of the new book.
     * @param editionNumber The edition number of the new book.
     * @param copyright The copyright year of the new book.
     * @param author_id The ID of the author associated with the new book.
     * @return A success message if saved, otherwise an error message if the author isn't found.
     */
    @PostMapping(path=BOOK)
    public @ResponseBody
    String addNewBook(@RequestParam String isbn, @RequestParam String title, @RequestParam int editionNumber, @RequestParam String copyright, @RequestParam Integer author_id){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setEditionNumber(editionNumber);
        book.setCopyright(copyright);
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            book.getAuthorList().add(author.get());
            bookRepository.save(book);
            return "Saved";
        }
        return "Author not found!";
    }

    /**
     * Updates an existing book identified by ISBN with new details.
     * @param isbn The ISBN of the book to update.
     * @param title New title for the book.
     * @param editionNumber New edition number for the book.
     * @param copyright New copyright year for the book.
     * @param author_id The ID of the new author associated with the book.
     * @return A success message if the book is updated, "Author not found!" if the author ID is invalid,
     *         or "Book not found!" if the book does not exist.
     */
    @PutMapping(path = BOOK + "/{isbn}")
    public @ResponseBody String updateBook(@PathVariable String isbn, @RequestParam String title, @RequestParam int editionNumber, @RequestParam String copyright, @RequestParam Integer author_id) {
        Optional<Book> bookOptional = bookRepository.findById(isbn);
        if(bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setTitle(title);
            book.setEditionNumber(editionNumber);
            book.setCopyright(copyright);
            Optional<Author> author = authorRepository.findById(author_id);
            if(author.isPresent()){
                book.getAuthorList().clear();
                book.getAuthorList().add(author.get());
                bookRepository.save(book);
                return "Book Updated";
            } else {
                return "Author not found!";
            }
        }
        return "Book not found!";
    }

    /**
     * Deletes a book from the repository identified by ISBN.
     * @param isbn The ISBN of the book to delete.
     * @return A success message if the book is deleted, otherwise a failure message if the book is not found.
     */
    @DeleteMapping(path = BOOK + "/{isbn}")
    public @ResponseBody String deleteBook(@PathVariable String isbn) {
        Optional<Book> book = bookRepository.findById(isbn);
        if(book.isPresent()){
            bookRepository.delete(book.get());
            return "Book Deleted";
        }
        return "Book not found";
    }

    /**
     * Retrieves all authors from the repository.
     * @return an iterable collection of all authors.
     */
    @GetMapping(path=AUTHORS)
    public @ResponseBody
    Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    /**
     * Retrieves an author by their ID.
     * @param author_id The ID of the author to retrieve.
     * @return An Optional containing the author if found, or an empty Optional if not found.
     */
    @GetMapping(path=AUTHORS + "/{author_id}")
    public @ResponseBody
    Optional<Author> getAuthorWithId(@PathVariable Integer author_id){
        return authorRepository.findById(author_id);
    }

    /**
     * Adds a new author to the repository.
     * @param firstName The first name of the author.
     * @param lastName The last name of the author.
     * @return A success message if the author is saved.
     */
    @PostMapping(path=AUTHORS)
    public @ResponseBody
    String addNewAuthor(@RequestParam String firstName, @RequestParam String lastName){
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return "Saved";
    }


    /**
     * Updates an existing author's details.
     * @param author_id The ID of the author to update.
     * @param firstName The new first name for the author.
     * @param lastName The new last name for the author.
     * @return A success message if updated, otherwise "Author not found!".
     */
   @PutMapping(path=AUTHORS + "/{author_id}")
   public @ResponseBody
   String updateAuthor(@PathVariable Integer author_id, @RequestParam String firstName, @RequestParam String lastName) {
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            Author author1 = author.get();
            author1.setFirstName(firstName);
            author1.setLastName(lastName);
            authorRepository.save(author1);
            return "Updated";
        }
        return "Author not found!";
   }

    /**
     * Deletes an author from the repository identified by their ID.
     * @param author_id The ID of the author to delete.
     * @return A success message if the author is deleted, otherwise "Author not found!".
     */
   @DeleteMapping(path=AUTHORS + "/{author_id}")
   public @ResponseBody
   String deleteAuthor(@PathVariable Integer author_id){
        Optional<Author> author = authorRepository.findById(author_id);
        if(author.isPresent()){
            authorRepository.delete(author.get());
            return "Deleted";
        }
        return "Author not found";
   }

}
