package es.codeurjc.readmebookstore.controller.rest;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.service.AlgorithmService;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.ReviewService;
import es.codeurjc.readmebookstore.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private AlgorithmService algorithmService;

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

    @Operation(summary = "Get a page of searched books or all books if no seachtext is inserted")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the books page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {Book.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Books page not found", content = @Content)
    })
    @GetMapping("")
    public ResponseEntity<Page<Book>> getSearchedBooks(@RequestParam(required = false) String searchtext, @RequestParam(defaultValue = "0") int page) {
        if (searchtext != null) {
            Boolean result = false;
            result = bookService.doSearchTitle(searchtext);
            if (result) {
                Page<Book> books = bookService.findPageTitle(searchtext, page);
                return new ResponseEntity<>(books, HttpStatus.OK);
            } else {
                result = bookService.doSearchAuthor(searchtext);
                if (result) {
                    Page<Book> books = bookService.findPageAuthor(searchtext, page);
                    return new ResponseEntity<>(books, HttpStatus.OK);
                } else {
                    result = bookService.doSearchGenre(searchtext);
                    if (result) {
                        Page<Book> books = bookService.findPageGenre(searchtext, page);
                        return new ResponseEntity<>(books, HttpStatus.OK);
                    } else {
                        result = bookService.doSearchPartial(searchtext);
                        if (result) {
                            Page<Book> books = bookService.findPagePartial(searchtext, page);
                            return new ResponseEntity<>(books, HttpStatus.OK);
                        } else {
                            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                        }
                    }
                }
            }
        } else {
            Page<Book> books = bookService.findAll(page);
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get the list of books recommended to a user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found the books to recommend", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {Book.class })) }),
                        @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Recommended books not found", content = @Content)
        })
    @GetMapping("/recommended")
    private ResponseEntity<List<Book>> recommendationAlgorithm(HttpServletRequest request) throws Exception {
		List<Long> recommendedBooksIds = algorithmService.recommendationAlgorithm (request);
		List<Book> recommendedBooks = new ArrayList<Book>();

		for (int i = 0; i < recommendedBooksIds.size(); i++) {
			Book book = bookService.findById(recommendedBooksIds.get(i)).get();
			recommendedBooks.add(book);
		}
        if (recommendedBooks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(recommendedBooks, HttpStatus.OK);
        }
    }

    @Operation(summary = "Get the top book to recommended a user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found the book to recommend", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {Book.class })) }),
                        @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Recommended book not found", content = @Content)
        })
    @GetMapping("/bestpick")
    private ResponseEntity<Book> bestRecommendation(HttpServletRequest request) throws Exception {
		List<Long> recommendedBooksIds = algorithmService.recommendationAlgorithm (request);
		
        Book book = bookService.findById(recommendedBooksIds.get(0)).get();
        if (book.equals(null)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
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

    
    @Operation(summary = "Add a new review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @PostMapping("/{bookId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Review> createReview(@PathVariable long bookId, @RequestBody Review newReview,
            HttpServletRequest request) throws SQLException {
        Optional<Book> book = bookService.findById(bookId);
        if (book.isPresent()) {
            String username = request.getUserPrincipal().getName();

            newReview.setAuthor(userService.findByName(username));
            newReview.setBook(book.get());
            newReview.setDate(new Date());

            reviewService.save(newReview);
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/reviews/{id}")
                .buildAndExpand(newReview.getId()).toUri();
            return ResponseEntity.created(location).body(newReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add a new offer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @PostMapping("/{bookId}/offers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Offer> createOffer(@PathVariable long bookId, @RequestBody Offer newOffer,
            HttpServletRequest request) throws SQLException {
        Optional<Book> book = bookService.findById(bookId);
        if (book.isPresent()) {

            String username = request.getUserPrincipal().getName();
            newOffer.setSeller(userService.findByName(username));
            newOffer.setBook(book.get());
            newOffer.setDate(new Date());

            offerService.save(newOffer);
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/offers/{id}")
                .buildAndExpand(newOffer.getId()).toUri();
            return ResponseEntity.created(location).body(newOffer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
