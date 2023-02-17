package es.codeurjc.readmebookstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/greeting")
	public String greeting(Model model) {

		model.addAttribute("name", "Mundo");

		return "greeting_template";
	}

	@GetMapping("/")
	public String home(Model model) {

		return "index";
	}

	@GetMapping("/books-general-page.html")
	public String books(Model model) {

		return "books-general-page";
	}
    
} 