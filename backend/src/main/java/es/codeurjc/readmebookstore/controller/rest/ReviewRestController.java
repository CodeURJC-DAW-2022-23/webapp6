package es.codeurjc.readmebookstore.controller.rest;

import java.util.List;
import java.util.Optional;

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

import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public List<Review> getReviews() {
        return reviewService.findAll();
    }

    @GetMapping("")
    public Page <Review> getReviewsPaged(@RequestParam int page) {
        return reviewService.findAll(page);
    }

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

     
    // DOES NOT WORK YET (method not allowed)
    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable long id) {
        Review review= reviewService.findById(id).get();
        if (review != null) {
            reviewService.delete(id);
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // POST AND PUT 

}


