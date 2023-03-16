package es.codeurjc.readmebookstore.controller;

import java.util.Optional;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.ReviewService;
import es.codeurjc.readmebookstore.service.UserService;
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


 
	@PostMapping("/books/{id}/reviews")
	public String uploadedReview(Model model, @PathVariable long id, @RequestParam String text, HttpServletRequest request) {
        Optional<Book> book = bookService.findById(id);
        String username = request.getUserPrincipal().getName();
		User user = userService.findByName(username);
        Date date = new Date();
        Review review = new Review(text, date, book.get(), user);
		reviewService.save(review);
		return "redirect:/books/"+ book.get().getId();
	}


    @GetMapping("/upload-review/{id}")
	public String uploadReview(Model model, @PathVariable long id) {
        Optional<Book> book = bookService.findById(id);
        model.addAttribute("book", book.get());
		return "upload-review-page";
	}

    @GetMapping("/update-review/{id}")
	public String modifyReview(Model model, @PathVariable long id) {
        Optional<Review> review = reviewService.findById(id);
        model.addAttribute("review", review.get());
		return "update-review-page";
	}

    @PostMapping("/reviews/{id}/update")
	public String updatedReview(Model model, @PathVariable long id, @RequestParam String text) {
        Review review = reviewService.findById(id).get();
        Date date = new Date();
        review.setDate(date);
        review.setText(text);
		reviewService.save(review);
		return "redirect:/user";
	}
  

    @GetMapping("/reviews/{id}/delete")
	public String deleteReview(Model model, @PathVariable long id) {
        reviewService.delete(id);
		return "redirect:/user";
	}
}