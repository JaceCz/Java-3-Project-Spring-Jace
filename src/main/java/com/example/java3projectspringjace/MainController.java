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

    @DeleteMapping(path = BOOK + "/{isbn}")
    public @ResponseBody String deleteBook(@PathVariable String isbn) {
        Optional<Book> book = bookRepository.findById(isbn);
        if(book.isPresent()){
            bookRepository.delete(book.get());
            return "Book Deleted";
        }
        return "Book not found";
    }



    @GetMapping(path=AUTHORS)
    public @ResponseBody
    Iterable<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    @GetMapping(path=AUTHORS + "/{author_id}")
    public @ResponseBody
    Optional<Author> getAuthorWithId(@PathVariable Integer author_id){
        return authorRepository.findById(author_id);
    }

    @PostMapping(path=AUTHORS)
    public @ResponseBody
    String addNewAuthor(@RequestParam String firstName, @RequestParam String lastName){
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorRepository.save(author);
        return "Saved";
    }

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
