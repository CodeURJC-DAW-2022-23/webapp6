package es.codeurjc.readmebookstore.controller.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact-page";
    }

    @GetMapping("/admin")
    public String admin(Model model,  @RequestParam(defaultValue = "0") int currentUsersPage, @RequestParam(defaultValue = "0") int currentBooksPage, 
            @RequestParam(defaultValue = "0") int currentReviewsPage, @RequestParam(defaultValue = "0") int currentOffersPage, HttpServletRequest request) {

        long id =  44;

        Page<User> userList = userService.findAll(id, currentUsersPage);
        //userList.remove(0); // The admin is removed so it displays diferently.

        model.addAttribute("userList", userList);
        
        model.addAttribute("bookList", bookService.findAll(currentBooksPage));

        model.addAttribute("reviewList", reviewService.findAll(currentReviewsPage));

        model.addAttribute("offerList", offerService.findAll(currentOffersPage));

        return "admin-page";
    }

    @PostMapping("/admin/books")
    public String addBook(Model model, @RequestParam String title, @RequestParam String genre,
            @RequestParam String author, MultipartFile imageField) throws IOException {
        Book newBook = new Book(title, author, genre);

        if (!imageField.isEmpty()) {
            newBook.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            newBook.setImage(true);
        }
        bookService.save(newBook);
        return "redirect:/admin";
    }

    // EDIT DATA
    ////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/admin/users/{id}/update")
    public String editUser(Model model, @PathVariable long id, @RequestParam String email, MultipartFile imageField) {
        User user = userService.findById(id).get();
        user.setEmail(email);
        userService.save(user);

        try {
            userService.updateImage(user, false, imageField);
        } catch (Exception e) {
            return "redirect:/admin";
        }
        userService.save(user);

        return "redirect:/admin";
    }

    @PostMapping("/admin/offers/{id}/update")
    public String editOffer(Model model, @PathVariable long id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam String edition, @RequestParam String description, @RequestParam Float price,
            MultipartFile imageField) {
        Offer offer = offerService.findById(id).get();
        offer.setDate(date);
        offer.setEdition(edition);
        offer.setDescription(description);
        offer.setPrice(price);
        offerService.save(offer);

        try {
            offerService.updateImage(offer, false, imageField);
        } catch (Exception e) {
            return "redirect:/admin";
        }
        offerService.save(offer);

        return "redirect:/admin";
    }

    @PostMapping("/admin/reviews/{id}/update")
    public String editReview(Model model, @PathVariable long id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam String text) {
        Review review = reviewService.findById(id).get();
        review.setDate(date);
        review.setText(text);
        reviewService.save(review);
        return "redirect:/admin";
    }

    @PostMapping("/admin/books/{id}/update")
    public String editBook(Model model, @PathVariable long id, @RequestParam String title, @RequestParam String genre,
            @RequestParam String author, MultipartFile imageField) {
        Book book = bookService.findById(id).get();
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthor(author);
        bookService.save(book);

        try {
            bookService.updateImage(book, false, imageField);
        } catch (Exception e) {
            return "redirect:/admin";
        }
        bookService.save(book);

        return "redirect:/admin";
    }

    // DELETE DATA
    // ///////////////////////////////////////////////////////////////////////////////////

    @RequestMapping("/admin/users/{id}/delete")
    public String deleteUser(Model model, @PathVariable long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/offers/{id}/delete")
    public String deleteOffer(Model model, @PathVariable long id) {
        offerService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/reviews/{id}/delete")
    public String deleteReview(Model model, @PathVariable long id) {
        reviewService.delete(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/books/{id}/delete")
    public String deleteBook(Model model, @PathVariable long id) {
        bookService.delete(id);
        return "redirect:/admin";
    }

}
