package es.codeurjc.readmebookstore.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.ReviewService;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable long id) {
        Optional<Book> op = bookService.findById(id);
        if (op.isPresent()) {
            Book book = op.get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DOES NOT WORK YET
    // @GetMapping("/{id}/reviews/")
    // public ResponseEntity<List<Review>> getBookReviews(@PathVariable long id) {
    // Optional<Book> op = bookService.findById(id);
    // if (op.isPresent()) {
    // List<Review> reviews = reviewService.findAllReviewsByBook(id);
    // return new ResponseEntity<>(reviews, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // }

    // DOES NOT WORK YET
    @GetMapping("/{id}/reviews/")
    public List<Review> getBookReviews(@PathVariable long id) {
        return reviewService.findAllReviewsByBook(id);
    }

    // DOES NOT WORK YET
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {

        bookService.save(book);

        return book;
    }
}
