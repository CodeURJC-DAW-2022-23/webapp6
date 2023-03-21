package es.codeurjc.readmebookstore.controller.rest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OfferService offerService;

    @Operation(summary = "Get all books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class))) }),
            @ApiResponse(responseCode = "404", description = "Books not found", content = @Content)
    })
    @GetMapping("/")
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @Operation(summary = "Get a page of books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Book.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book page not found", content = @Content)
    })
    @GetMapping("")
    public Page<Book> getBooks(@RequestParam int page) {
        return bookService.findAll(page);
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
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

    @Operation(summary = "Get all the reviews of a specified book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the reviews", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Review.class))) }),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @GetMapping("/{id}/reviews/all")
    public ResponseEntity<List<Review>> getBookReviews(@PathVariable long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            List<Review> reviews = reviewService.findAllReviewsByBook(id);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a page of the reviews of a specified book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the reviews page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Review.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @GetMapping("/{id}/reviews")
    public ResponseEntity<Page<Review>> getBookReviews(@PathVariable long id, @RequestParam int page) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            Page<Review> reviews = reviewService.findAllReviewsByBook(id, page);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all the offers of a specified book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the offers", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Offer.class))) }),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @GetMapping("/{id}/offers/all")
    public ResponseEntity<List<Offer>> getBookOffers(@PathVariable long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            List<Offer> offers = offerService.findOffersNotSoldByBook(id);
            return new ResponseEntity<>(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a page of the offers of a specified book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the offers page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Offer.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @GetMapping("/{id}/offers")
    public ResponseEntity<Page<Offer>> getBookOffers(@PathVariable long id, @RequestParam int page) {
        Optional<Book> op = bookService.findById(id);
        if (op.isPresent()) {
            Page<Offer> offers = offerService.findOffersNotSoldByBook(id, page);
            return new ResponseEntity<>(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "The image of a specified book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the image", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Resource.class))) }),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @GetMapping("/{id}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent() && book.get().getImageFile() != null) {
            Resource file = new InputStreamResource(book.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(book.get().getImageFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
