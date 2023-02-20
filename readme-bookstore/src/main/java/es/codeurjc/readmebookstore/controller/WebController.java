package es.codeurjc.readmebookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}

	@GetMapping("/index.html")
	public String index(Model model) {
		return "index";
	}

	@GetMapping("/admin-page.html")
	public String admin(Model model) {
		return "admin-page";
	}

	@GetMapping("/book-particular-page.html")
	public String bookParticular(Model model) {
		return "book-particular-page";
	}

	@GetMapping("/books-general-page.html")
	public String booksGeneral(Model model) {
		return "books-general-page";
	}

	@GetMapping("/checkout-page.html")
	public String checkout(Model model) {
		return "checkout-page";
	}

	@GetMapping("/contact-page.html")
	public String contact(Model model) {
		return "contact-page";
	}

	@GetMapping("/login-page.html")
	public String login(Model model) {
		return "login-page";
	}

	@GetMapping("/offer-page.html")
	public String offer(Model model) {
		return "offer-page";
	}

	@GetMapping("/register-page.html")
	public String register(Model model) {
		return "register-page";
	}

	@GetMapping("/statistics-page.html")
	public String statistics(Model model) {
		return "statistics-page";
	}

	@GetMapping("/upload-offer-page.html")
	public String uploadOffer(Model model) {
		return "upload-offer-page";
	}

	@GetMapping("/upload-review-page.html")
	public String uploadReview(Model model) {
		return "upload-review-page";
	}

	@GetMapping("/user-page.html")
	public String user(Model model) {
		return "user-page";
	}

} 