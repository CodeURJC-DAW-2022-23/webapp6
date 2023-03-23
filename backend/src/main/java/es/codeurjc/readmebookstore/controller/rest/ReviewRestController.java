package es.codeurjc.readmebookstore.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/reviews")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    ///////////////////// GETS ////////////////////////////////////

    @Operation(summary = "Get all reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the reviews", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Review.class))) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "404", description = "Reviews not found", content = @Content)
    })
    @GetMapping("/")
    public List<Review> getReviews() {
        return reviewService.findAll();
    }

    @Operation(summary = "Get a page of all reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the review page", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class, subTypes = {
                            Review.class })) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "404", description = "Review page not found", content = @Content)
    })
    @GetMapping("")
    public Page<Review> getReviewsPaged(@RequestParam(defaultValue = "0")  int page) {
        return reviewService.findAll(page);
    }

    @Operation(summary = "Get a review by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the review ", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Review.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request, try again", content = @Content),
            @ApiResponse(responseCode = "404", description = "Review not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable long id) {
        Optional<Review> op = reviewService.findById(id);
        if (op.isPresent()) {
            Review review = op.get();
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
