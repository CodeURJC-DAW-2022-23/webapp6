package es.codeurjc.readmebookstore.controller;

import java.util.Optional;
import java.util.Date;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import es.codeurjc.readmebookstore.repository.ReviewRepository;
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReviewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;


 
	@PostMapping("/uploadedReview/{id}")
	public String uploadedReview(Model model, @PathVariable long id, @RequestParam String text, HttpServletRequest request) {
        Optional<Book> book = bookRepository.findById(id);
        String username = request.getUserPrincipal().getName();
		User user = userRepository.findByName(username).orElseThrow();
        Date date = new Date();
        Review review = new Review(text, date, book.get(), user);
		reviewRepository.save(review);
		return "redirect:/book/"+ book.get().getId();
	}


    @GetMapping("/uploadReview/{id}")
	public String uploadReview(Model model, @PathVariable long id) {
        Optional<Book> book = bookRepository.findById(id);
        model.addAttribute("book", book.get());
		return "upload-review-page";
	}

    @GetMapping("/modifyReview/{id}")
	public String modifyReview(Model model, @PathVariable long id) {
        Optional<Review> review = reviewRepository.findById(id);
        model.addAttribute("review", review.get());
		return "update-review-page";
	}

    @PostMapping("/updatedReview/{id}")
	public String updatedReview(Model model, @PathVariable long id, @RequestParam String text) {
        Optional<Review> review = reviewRepository.findById(id);
        Date date = new Date();
        review.get().setDate(date);
        review.get().setText(text);
		reviewRepository.save(review.get());
		return "redirect:/user-page";
	}
  

    @GetMapping("/deleteReview/{id}")
	public String deleteReview(Model model, @PathVariable long id) {
        reviewRepository.deleteById(id);
		return "redirect:/user-page";
	}
}