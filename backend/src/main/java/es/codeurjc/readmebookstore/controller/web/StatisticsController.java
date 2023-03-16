package es.codeurjc.readmebookstore.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.ReviewService;
import es.codeurjc.readmebookstore.service.UserService;

@Controller
public class StatisticsController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OfferService offerService;

    @GetMapping("/statistics")
    public String statistics(Model model) {

        int userNumber = userService.findAll().size();
        model.addAttribute("userNumber", userNumber);

        int bookNumber = bookService.findAll().size();
        model.addAttribute("bookNumber", bookNumber);

        int reviewNumber = reviewService.findAll().size();
        model.addAttribute("reviewNumber", reviewNumber);

        int offerNumber = offerService.findAll().size();
        model.addAttribute("offerNumber", offerNumber);

        return "statistics-page";
    }
}
