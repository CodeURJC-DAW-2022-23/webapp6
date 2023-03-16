package es.codeurjc.readmebookstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.service.BookService;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private BookService bookService;

    // DOES NOT WORK YET
    @PostMapping("/books/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {

        bookService.save(book);

        return book;
    }

    // DOES NOT WORK YET
    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteBook(@PathVariable long id) {

        bookService.delete(id);

        return "Book deleted";
    }
}