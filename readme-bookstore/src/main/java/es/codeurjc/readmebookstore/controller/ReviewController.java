package es.codeurjc.readmebookstore.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;


	@PostMapping("/uploaded-review/{id}")
	public String uploadedReview(Model model, @PathVariable long id, @RequestParam String text, HttpServletRequest request) {
        Book book = bookService.findById(id).get();
        String username = request.getUserPrincipal().getName();
		User user = userService.findByName(username);
        Date date = new Date();
        Review review = new Review(text, date, book, user);
		reviewService.save(review);
		return "redirect:/book/"+ book.getId();
	}


    @GetMapping("/upload-review/{id}")
	public String uploadReview(Model model, @PathVariable long id) {
        Book book = bookService.findById(id).get();
        model.addAttribute("book", book);
		return "upload-review-page";
	}

    @GetMapping("/modify-review/{id}")
	public String modifyReview(Model model, @PathVariable long id) {
        Review review = reviewService.findById(id).get();
        model.addAttribute("review", review);
		return "update-review-page";
	}

    @PostMapping("/updated-review/{id}")
	public String updatedReview(Model model, @PathVariable long id, @RequestParam String text) {
        Review review = reviewService.findById(id).get();
        Date date = new Date();
        review.setDate(date);
        review.setText(text);
		reviewService.save(review);
		return "redirect:/user-page";
	}
  

    @GetMapping("/delete-review/{id}")
	public String deleteReview(Model model, @PathVariable long id) {
        reviewService.delete(id);
		return "redirect:/user-page";
	}
}