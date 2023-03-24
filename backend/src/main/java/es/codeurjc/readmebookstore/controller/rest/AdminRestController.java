package es.codeurjc.readmebookstore.controller.rest;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
import org.springframework.web.multipart.MultipartFile;

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
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OfferService offerService;

    ///////////////// BOOKS //////////////////////////////////////////////////
    
    @Operation(summary = "Add a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content)
    })
    @PostMapping("/books/")
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book newBook) {
        bookService.save(newBook);
        return newBook;
    }

    @Operation(summary = "Add book's image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image upload", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @PostMapping("/books/{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            Book bookUpdate = book.get();
            URI location = fromCurrentRequest().build().toUri();

            bookUpdate.setImage(true);
            bookUpdate.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
            bookService.save(bookUpdate);

            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Update a book data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book data updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> editBook(@PathVariable long id, @RequestBody Book newData) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            Book bookUpdate = book.get();

            bookUpdate.setTitle(newData.getTitle());
            bookUpdate.setAuthor(newData.getAuthor());
            bookUpdate.setGenre(newData.getGenre());

            bookService.save(bookUpdate);
            return ResponseEntity.ok(bookUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable long id) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            bookService.delete(id);
            Book deletedBook = book.get();
            return ResponseEntity.ok(deletedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a book's image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @DeleteMapping("/books/{id}/image")
    public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            Book bookUpdate = book.get();
            bookUpdate.setImageFile(null);
            bookUpdate.setImage(false);
            bookService.save(bookUpdate);
            return ResponseEntity.ok("Imagen eliminada");
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

    @Operation(summary = "Delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete the user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
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

    @Operation(summary = "Update a user profile by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User profile updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,
            @RequestBody User updatedUser) throws SQLException {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User userSession = user.get();
            updatedUser.setId(userSession.getId());
            updatedUser.setName(userSession.getName());
            updatedUser.setEncodedPassword(userSession.getEncodedPassword());
            updatedUser.setListFavouriteBooks(userSession.getFavouriteBooks());
            updatedUser.setOffers(userSession.getOffers());
            updatedUser.setReadedReviews(userSession.getReadedReviews());
            updatedUser.setRoles(userSession.getRoles());
            updatedUser.setImage(userSession.getImage());
            updatedUser.setImageFile(userSession.getImageFile());
            userService.save(updatedUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Upload the user image ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image upload", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @PostMapping("/users/{id}/image")
    public ResponseEntity<Object> uploadImageUser(@PathVariable long id, @RequestParam MultipartFile imageFile)
            throws IOException {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User userUpdate = user.get();
            URI location = fromCurrentRequest().build().toUri();

            userUpdate.setImage(true);
            userUpdate.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
            userService.save(userUpdate);

            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a user's image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @DeleteMapping("/users/{id}/image")
    public ResponseEntity<Object> deleteImageUser(@PathVariable long id) throws IOException {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User userUpdate = user.get();
            userUpdate.setImageFile(null);
            userUpdate.setImage(false);
            userService.save(userUpdate);
            return ResponseEntity.ok("Imagen eliminada");
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
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable long id) {
        Optional<Review> opReview = reviewService.findById(id);
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
        Optional<Review> opReview = reviewService.findById(id);
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


           /////////////////////// Offers //////////////////////////////////////////////

   @Operation(summary = "Add offers's image")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Image upload", content = {
                   @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
           @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
           @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
           @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
   })
   @PostMapping("/offers/{id}/image")
   public ResponseEntity<Object> uploadImageOffer(@PathVariable long id, @RequestParam MultipartFile imageFile)
           throws IOException {
       Optional<Offer> offer = offerService.findById(id);
       if (offer.isPresent()) {
           Offer offerUpdate = offer.get();
           URI location = fromCurrentRequest().build().toUri();

           offerUpdate.setImage(true);
           offerUpdate.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
           offerService.save(offerUpdate);

           return ResponseEntity.created(location).build();
       } else {
           return ResponseEntity.notFound().build();
       }
   }

   @Operation(summary = "Update a offer data")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Offer data updated", content = {
                   @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
           @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
           @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
           @ApiResponse(responseCode = "404", description = "Offer not found", content = @Content)
   })
   @PutMapping("/offers/{id}")
   public ResponseEntity<Offer> updateOffer(@PathVariable long id,
            @RequestBody Offer updatedOffer) throws SQLException {
        Optional <Offer> opOffer = offerService.findById(id);
        if (opOffer.isPresent()) {
                Offer offer = opOffer.get();
                updatedOffer.setId(id);
                updatedOffer.setSeller(offer.getSeller());
                updatedOffer.setBook(offer.getBook());
                updatedOffer.setSold(offer.getSold());
                Date date = new Date();
                updatedOffer.setDate(date);
                offerService.save(updatedOffer);
                return ResponseEntity.ok(updatedOffer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @Operation(summary = "Delete a offer")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Offer deleted", content = {
                   @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
           @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
           @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
           @ApiResponse(responseCode = "404", description = "Offer not found", content = @Content)
   })
   @DeleteMapping("/offers/{id}")
   public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
       Optional<Offer> offer = offerService.findById(id);
       if (offer.isPresent()) {
           offerService.delete(id);
           Offer deletedOffer = offer.get();
           return ResponseEntity.ok(deletedOffer);
       } else {
           return ResponseEntity.notFound().build();
       }
   }

   @Operation(summary = "Delete a offer's image")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Image deleted", content = {
                   @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
           @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
           @ApiResponse(responseCode = "403", description = "Unauthorized action, login as an admin", content = @Content),
           @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
   })
   @DeleteMapping("/offers/{id}/image")
   public ResponseEntity<Object> deleteImageOffer(@PathVariable long id) throws IOException {
       Optional<Offer> offer = offerService.findById(id);
       if (offer.isPresent()) {
           Offer offerUpdate = offer.get();
           offerUpdate.setImageFile(null);
           offerUpdate.setImage(false);
           offerService.save(offerUpdate);
           return ResponseEntity.ok("Imagen eliminada");
       } else {
           return ResponseEntity.notFound().build();
       }
   }
}