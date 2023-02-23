package es.codeurjc.readmebookstore.controller;

import java.util.Optional;
import java.util.Date;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.repository.BookRepository;
import es.codeurjc.readmebookstore.repository.OfferRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OfferController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OfferRepository offerRepository;


 
	@PostMapping("/uploadedOffer/{id}")
	public String uploadedReview(Model model, @PathVariable long id, @RequestParam String edition, @RequestParam String text, @RequestParam float price, HttpServletRequest request) {
        Optional<Book> book = bookRepository.findById(id);
        String username = request.getUserPrincipal().getName();
		User user = userRepository.findByName(username).orElseThrow();
        Date date = new Date();
        Offer offer = new Offer(date, edition, text, price, book.get(), user);
		offerRepository.save(offer);
		return "redirect:/book/"+ book.get().getId();
	}


    @GetMapping("/uploadOffer/{id}")
	public String uploadOffer(Model model, @PathVariable long id) {
        Optional<Book> book = bookRepository.findById(id);
        model.addAttribute("book", book.get());
		return "upload-offer-page";
	}

  

}