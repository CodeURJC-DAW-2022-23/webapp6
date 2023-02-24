package es.codeurjc.readmebookstore.controller;

import java.security.Principal;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class WebController {

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("id", request.getRequestedSessionId());
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}

	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
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

	@GetMapping("/offer-page.html")
	public String offer(Model model) {
		return "offer-page";
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

}