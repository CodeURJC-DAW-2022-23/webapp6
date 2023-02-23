package es.codeurjc.readmebookstore.controller;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.repository.ReviewRepository;
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ReviewController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

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
		return "index";
	}


    @GetMapping("/uploadReview/{id}")
	public String uploadReview(Model model, @PathVariable long id) {
        Optional<Book> book = bookRepository.findById(id);
        model.addAttribute("book", book.get());
		return "upload-review-page";
	}

  

}