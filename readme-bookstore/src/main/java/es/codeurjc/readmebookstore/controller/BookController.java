package es.codeurjc.readmebookstore.controller;
import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookController {


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


 
	@GetMapping("/books")
	public String showBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
		return "books-general-page";
	}

    @GetMapping("/book/{id}")
	public String showBook(Model model, @PathVariable long id) {
        Optional<Book> book = bookRepository.findById(id);
        model.addAttribute("book", book.get());
        model.addAttribute("reviews", book.get().getReviews());
        model.addAttribute("offers", book.get().getOffers());
		return "book-particular-page";
	}

  

}
