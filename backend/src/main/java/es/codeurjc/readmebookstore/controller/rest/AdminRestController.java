package es.codeurjc.readmebookstore.controller.rest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.ReviewService;
import es.codeurjc.readmebookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    ///////////////// BOOKS //////////////////////////////////////////////////
    @PostMapping("books/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        bookService.save(book);
        return book;
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable long id) {
        Optional<Book> op = bookService.findById(id);
        if (op.isPresent()) {
            Book book = op.get();
            bookService.delete(id);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    ///////////////// USERS //////////////////////////////////////////////////

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/users/{idUser}")
    public ResponseEntity<User> getUser(@PathVariable long idUser) {
        Optional<User> op = userService.findById(idUser);
        if (op.isPresent()) {
            User user = op.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)
    })
    @GetMapping("/users/")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "Get a users page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            User.class })) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Users page not found", content = @Content)
    })
    @GetMapping("/users")
    public Page<User> getUsersPaged(@RequestParam(defaultValue = "0") int page) {
        return userService.findAll(page);
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            User.class })) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Users not found", content = @Content)
    })
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {
        Optional<User> op = userService.findById(id);
        if (op.isPresent()) {
            User user = op.get();
            userService.delete(id);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /////////////////////// REVIEWS //////////////////////////////////////////////

    @Operation(summary = "Delete a review by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable long id) {
        Optional <Review> opReview = reviewService.findById(id);
        if (opReview.isPresent()) {
            Review review = opReview.get();
            reviewService.delete(id);
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a review by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id,
            @RequestBody Review updatedReview) {
        Optional <Review> opReview = reviewService.findById(id);
        if (opReview.isPresent()) {
            Review review = opReview.get();
            updatedReview.setId(id);
            updatedReview.setAuthor(review.getAuthor());
            updatedReview.setBook(review.getBook());
            Date date = new Date();
            updatedReview.setDate(date);
            reviewService.save(updatedReview);
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}