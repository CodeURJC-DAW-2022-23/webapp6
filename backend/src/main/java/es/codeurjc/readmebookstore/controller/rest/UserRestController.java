package es.codeurjc.readmebookstore.controller.rest;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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

    @Operation(summary = "Get the information of the user in the session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })

    @GetMapping("/")
    public ResponseEntity<User> me(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            return ResponseEntity.ok(userService.findByName(principal.getName()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Get user favorite books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user favorite books", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class))) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/books/")
    public ResponseEntity<List<Book>> getUserFavoriteBooks(HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
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
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/books")
    public ResponseEntity<Page<Book>> getUserFavoriteBooksPaginated(@RequestParam(defaultValue = "0") int page,
            HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
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
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/reviews/")
    public ResponseEntity<List<Review>> getUserReviews(HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
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
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/reviews")
    public ResponseEntity<Page<Review>> getUserReviewsPaginated(@RequestParam(defaultValue = "0") int page,
            HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
        Optional<User> op = userService.findById(user.getId());
        Page<Review> reviews = reviewService.findAllReviewsByUser(user.getId(),
                page);
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
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/offers/")
    public ResponseEntity<List<Offer>> getUserOffers(HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
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
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/offers")
    public ResponseEntity<Page<Offer>> getUserOffersPaginated(@RequestParam(defaultValue = "0") int page,
            HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
        Optional<User> op = userService.findById(user.getId());
        Page<Offer> offers = offerService.findOffersNotSoldByUser(user.getId(),
                page);
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
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/historial/")
    public ResponseEntity<List<Offer>> getUserHistorial(HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
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
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/historial")
    public ResponseEntity<Page<Offer>> getUserHistorialPaginated(@RequestParam(defaultValue = "0") int page,
            HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
        Optional<User> op = userService.findById(user.getId());
        Page<Offer> offers = offerService.findShoppingHistorial(user.getId(), page);
        if (op.isPresent()) {
            return new ResponseEntity<>(offers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "The image of the user in the session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the image", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @GetMapping("/image")
    public ResponseEntity<Object> downloadImage(HttpServletRequest request) throws SQLException {
        User userSession = userService.findByName(request.getUserPrincipal().getName());
        Optional<User> user = userService.findById(userSession.getId());
        if (user.isPresent() && user.get().getImageFile() != null) {
            Resource file = new InputStreamResource(user.get().getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(user.get().getImageFile().length()).body(file);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /////////////////////////// DELETES //////////////////////////////////////////

    @Operation(summary = "Delete a user favorite book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite book deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Favorite book not found", content = @Content)
    })
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Book> deleteFavoriteBook(@PathVariable long id) {
        Book book = bookService.findById(id).get();
        if (book != null) {
            userService.deletefavorite(id);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete a user review by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
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

    @Operation(summary = "Delete a user offer by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
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

    @Operation(summary = "Delete the user image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "image deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @DeleteMapping("/image")
    public ResponseEntity<String> deleteImage(HttpServletRequest request) throws IOException {
        User user = userService.findByName(request.getUserPrincipal().getName());
        user.setImageFile(null);
        user.setImage(false);
        userService.save(user);
        return ResponseEntity.ok("Imagen eliminada");
    }

    /////////////////////////// PUTS //////////////////////////////////////////

    @Operation(summary = "Update a user review ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id,
            @RequestBody Review updatedReview) {
        Review review = reviewService.findById(id).get();
        if (review != null) {
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

    @Operation(summary = "Update a user offer ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Offer updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Offer.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Offer not found", content = @Content)
    })
    @PutMapping("/offers/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable long id,
            @RequestBody Offer updatedOffer) throws SQLException {
        Offer offer = offerService.findById(id).get();
        if (offer != null) {
            if (updatedOffer.getImage()) {
                Offer dbOffer = offerService.findById(id).orElseThrow();
                if (dbOffer.getImage()) {
                    updatedOffer.setImageFile(BlobProxy.generateProxy(dbOffer.getImageFile().getBinaryStream(),
                            dbOffer.getImageFile().length()));
                }
            }
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

    @Operation(summary = "Update the user profile ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User profile updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping("/")
    public ResponseEntity<User> updateUser(
            @RequestBody User updatedUser, HttpServletRequest request) throws SQLException {
        User user = userService.findByName(request.getUserPrincipal().getName());
        if (user != null) {
            if (updatedUser.getImage()) {
                User dbUser = userService.findById(user.getId()).orElseThrow();
                if (dbUser.getImage()) {
                    updatedUser.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(),
                            dbUser.getImageFile().length()));
                }
            }
            updatedUser.setId(user.getId());
            updatedUser.setName(user.getName());
            updatedUser.setEncodedPassword(user.getEncodedPassword());
            updatedUser.setListFavouriteBooks(user.getFavouriteBooks());
            updatedUser.setOffers(user.getOffers());
            updatedUser.setReadedReviews(user.getReadedReviews());
            updatedUser.setRoles(user.getRoles());
            userService.save(updatedUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    //////////////////////////// POSTS //////////////////////////////////////

    @Operation(summary = "Upload the user image ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image upload", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Resource.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "403", description = "Unauthorized action, login", content = @Content),
            @ApiResponse(responseCode = "404", description = "Image not found", content = @Content)
    })
    @PostMapping("/image")
    public ResponseEntity<Object> uploadImage(@RequestParam MultipartFile imageFile, HttpServletRequest request)
            throws IOException {

        User user = userService.findByName(request.getUserPrincipal().getName());

        URI location = fromCurrentRequest().build().toUri();

        user.setImage(true);
        user.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
        userService.save(user);

        return ResponseEntity.created(location).build();
    }

}
