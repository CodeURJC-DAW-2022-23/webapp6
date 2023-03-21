package es.codeurjc.readmebookstore.controller.rest;

//import java.security.Principal;
import java.util.List;
import java.util.Optional;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.model.User;
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
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OfferService offerService;

    ///////////////////// GETS ////////////////////////////////////

    // Is commented the function getting de id by the request HttpservletRequest ( logged not implemented yet)
    // Is not commented putting the id manual for testing

    @Operation(summary = "Get logged user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found logged user profile", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    /*
     * @GetMapping("/")
     * public ResponseEntity<User> me(HttpServletRequest request) {
     * Principal principal = request.getUserPrincipal();
     * if (principal != null) {
     * return ResponseEntity.ok(userService.findByName(principal.getName()));
     * } else {
     * return ResponseEntity.notFound().build();
     * }
     * }
     */
    @GetMapping("/")
    public ResponseEntity<User> me() {
        return ResponseEntity.ok(userService.findByName("jose"));

    }

    @Operation(summary = "Get user favorite books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user favorite books", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class))) }),
            @ApiResponse(responseCode = "400", description = "Invalid format supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    /*
     * @GetMapping("/books/")
     * public ResponseEntity<List<Book>> getUserFavoriteBooks(HttpServletRequest
     * request) {
     * User user = userService.findByName(request.getUserPrincipal().getName());
     * Optional<User> op = userService.findById(user.getId());
     * List<Book> books = bookService.favoritesbooks(user.getId());
     * if (op.isPresent()) {
     * return new ResponseEntity<>(books, HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    @GetMapping("/books/")
    public ResponseEntity<List<Book>> getUserFavoriteBooks() {
        User user = userService.findByName("jose");
        Optional<User> op = userService.findById(user.getId());
        List<Book> books = bookService.favoritesbooks(user.getId());
        if (op.isPresent()) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get user favorite books page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user favorite books page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Book.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    /*
     * @GetMapping("/books")
     * public ResponseEntity<Page<Book>> getUserFavoriteBooksPaginated(@RequestParam
     * int page,
     * HttpServletRequest request) {
     * User user = userService.findByName(request.getUserPrincipal().getName());
     * Optional<User> op = userService.findById(user.getId());
     * Page<Book> books = bookService.favoriteBooks(user.getId(), page);
     * if (op.isPresent()) {
     * return new ResponseEntity<>(books, HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getUserFavoriteBooksPaginated(@RequestParam int page) {
        User user = userService.findByName("jose");
        Optional<User> op = userService.findById(user.getId());
        Page<Book> books = bookService.favoriteBooks(user.getId(), page);
        if (op.isPresent()) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get user reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user reviews", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Review.class))) }),
            @ApiResponse(responseCode = "400", description = "Invalid format supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    /*
     * @GetMapping("/reviews/")
     * public ResponseEntity<List<Review>> getUserReviews(HttpServletRequest
     * request) {
     * User user = userService.findByName(request.getUserPrincipal().getName());
     * Optional<User> op = userService.findById(user.getId());
     * List<Review> reviews = reviewService.findAllReviewsByUser(user.getId());
     * if (op.isPresent()) {
     * return new ResponseEntity<>(reviews, HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    @GetMapping("/reviews/")
    public ResponseEntity<List<Review>> getUserReviews() {
        User user = userService.findByName("jose");
        Optional<User> op = userService.findById(user.getId());
        List<Review> reviews = reviewService.findAllReviewsByUser(user.getId());
        if (op.isPresent()) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get user reviews page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user reviews page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Review.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    /*
     * @GetMapping("/reviews")
     * public ResponseEntity<Page<Review>> getUserReviewsPaginated(@RequestParam int
     * page, HttpServletRequest request) {
     * User user = userService.findByName(request.getUserPrincipal().getName());
     * Optional<User> op = userService.findById(user.getId());
     * Page<Review> reviews = reviewService.findAllReviewsByUser(user.getId(),
     * page);
     * if (op.isPresent()) {
     * return new ResponseEntity<>(reviews, HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    @GetMapping("/reviews")
    public ResponseEntity<Page<Review>> getUserReviewsPaginated(@RequestParam int page) {
        User user = userService.findByName("jose");
        Optional<User> op = userService.findById(user.getId());
        Page<Review> reviews = reviewService.findAllReviewsByUser(user.getId(), page);
        if (op.isPresent()) {
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get user offers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user offers", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Offer.class))) }),
            @ApiResponse(responseCode = "400", description = "Invalid format supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    /*
     * @GetMapping("/offers/")
     * public ResponseEntity<List<Offer>> getUserOffers(HttpServletRequest request)
     * {
     * User user = userService.findByName(request.getUserPrincipal().getName());
     * Optional<User> op = userService.findById(user.getId());
     * List<Offer> offers = offerService.findOffersNotSoldByUser(user.getId());
     * if (op.isPresent()) {
     * return new ResponseEntity<>(offers, HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    @GetMapping("/offers/")
    public ResponseEntity<List<Offer>> getUserOffers() {
        User user = userService.findByName("jose");
        Optional<User> op = userService.findById(user.getId());
        List<Offer> offers = offerService.findOffersNotSoldByUser(user.getId());
        if (op.isPresent()) {
            return new ResponseEntity<>(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get user offers page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user offers page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Offer.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    /*
     * @GetMapping("/offers")
     * public ResponseEntity<Page<Offer>> getUserOffersPaginated(@RequestParam int
     * page, HttpServletRequest request) {
     * User user = userService.findByName(request.getUserPrincipal().getName());
     * Optional<User> op = userService.findById(user.getId());
     * Page<Offer> offers = offerService.findOffersNotSoldByUser(user.getId(),
     * page);
     * if (op.isPresent()) {
     * return new ResponseEntity<>(offers, HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    @GetMapping("/offers")
    public ResponseEntity<Page<Offer>> getUserOffersPaginated(@RequestParam int page) {
        User user = userService.findByName("jose");
        Optional<User> op = userService.findById(user.getId());
        Page<Offer> offers = offerService.findOffersNotSoldByUser(user.getId(), page);
        if (op.isPresent()) {
            return new ResponseEntity<>(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get user buy-sell historial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user historial", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Offer.class))) }),
            @ApiResponse(responseCode = "400", description = "Invalid format supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    /*
     * @GetMapping("/historial/")
     * public ResponseEntity<List<Offer>> getUserHistorial(HttpServletRequest
     * request) {
     * User user = userService.findByName(request.getUserPrincipal().getName());
     * Optional<User> op = userService.findById(user.getId());
     * List<Offer> offers = offerService.findShoppingHistorial(user.getId());
     * if (op.isPresent()) {
     * return new ResponseEntity<>(offers, HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    @GetMapping("/historial/")
    public ResponseEntity<List<Offer>> getUserHistorial() {
        User user = userService.findByName("jose");
        Optional<User> op = userService.findById(user.getId());
        List<Offer> offers = offerService.findShoppingHistorial(user.getId());
        if (op.isPresent()) {
            return new ResponseEntity<>(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get user buy-sell historial page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user historial page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Offer.class })) }),
            @ApiResponse(responseCode = "400", description = "Invalid page supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    /*
     * @GetMapping("/historial")
     * public ResponseEntity<Page<Offer>> getUserHistorialPaginated(@RequestParam
     * int page, HttpServletRequest request) {
     * User user = userService.findByName(request.getUserPrincipal().getName());
     * Optional<User> op = userService.findById(user.getId());
     * Page<Offer> offers = offerService.findShoppingHistorial(user.getId(), page);
     * if (op.isPresent()) {
     * return new ResponseEntity<>(offers, HttpStatus.OK);
     * } else {
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * }
     * }
     */
    @GetMapping("/historial")
    public ResponseEntity<Page<Offer>> getUserHistorialPaginated(@RequestParam int page) {
        User user = userService.findByName("jose");
        Optional<User> op = userService.findById(user.getId());
        Page<Offer> offers = offerService.findShoppingHistorial(user.getId(), page);
        if (op.isPresent()) {
            return new ResponseEntity<>(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /////////////////////////// DELETES //////////////////////////////////////////

    @Operation(summary = "Delete an user favorite book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite book deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Favorite book not found", content = @Content)
    })
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteFavoriteBook(@PathVariable long id) {
        Book book = bookService.findById(id).get();
        if (book != null) {
            bookService.delete(id);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete an user review by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable long id) {
        Review review = reviewService.findById(id).get();
        if (review != null) {
            reviewService.delete(id);
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete an user offer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Offer not found", content = @Content)
    })
    @DeleteMapping("/offers/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer offer = offerService.findById(id).get();
        if (offer != null) {
            offerService.delete(id);
            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /////////////////////////// PUTS //////////////////////////////////////////

    



}
