package es.codeurjc.readmebookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.ReviewService;
import es.codeurjc.readmebookstore.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OfferService offerService;

    @GetMapping("/admin")
    public String admin(Model model) {

        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);

        List<Review> reviewList = reviewService.findAll();
        model.addAttribute("reviewList", reviewList);

        List<Offer> offerList = offerService.findAll();
        model.addAttribute("offerList", offerList);

        return "admin-page";
    }

}
