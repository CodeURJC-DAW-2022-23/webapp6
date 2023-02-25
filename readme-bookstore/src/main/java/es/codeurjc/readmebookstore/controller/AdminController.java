package es.codeurjc.readmebookstore.controller;

import java.io.IOException;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.model.Review;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.repository.BookRepository;
import es.codeurjc.readmebookstore.repository.OfferRepository;
import es.codeurjc.readmebookstore.repository.ReviewRepository;
import es.codeurjc.readmebookstore.repository.UserRepository;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.ReviewService;
import es.codeurjc.readmebookstore.service.UserService;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OfferRepository offerRepository;

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
        userList.remove(0); // The admin is removed so it displays diferently.
        model.addAttribute("userList", userList);

        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);

        List<Review> reviewList = reviewService.findAll();
        model.addAttribute("reviewList", reviewList);

        List<Offer> offerList = offerService.findAll();
        model.addAttribute("offerList", offerList);

        return "admin-page";
    }

    @GetMapping("/admin/add-book")
    public String addBook(Model model, @RequestParam String title, @RequestParam String genre,
            @RequestParam String author, MultipartFile imageField) throws IOException {
        Book newBook = new Book(title, author, genre);

        if (!imageField.isEmpty()) {
            newBook.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            newBook.setImage(true);
        }
        bookRepository.save(newBook);
        return "redirect:/admin";
    }

    // EDIT DATA
    // //////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping("/admin/edit-user/{id}")
    public String editUser(Model model, @PathVariable long id, @RequestParam String email) {
        User user = userService.findById(id).get();
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit-offer/{id}")
    public String editOffer(Model model, @PathVariable long id, @RequestParam String edition,
            @RequestParam String description, @RequestParam Float price) {
        Offer offer = offerService.findById(id).get();
        offer.setEdition(edition);
        offer.setDescription(description);
        offer.setPrice(price);
        offerRepository.save(offer);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit-review/{id}")
    public String editReview(Model model, @PathVariable long id, @RequestParam String text) {
        Review review = reviewService.findById(id).get();
        review.setText(text);
        reviewRepository.save(review);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit-book/{id}")
    public String editBook(Model model, @PathVariable long id, @RequestParam String title, @RequestParam String genre,
            @RequestParam String author) {
        Book book = bookService.findById(id).get();
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthor(author);
        bookRepository.save(book);

        return "redirect:/admin";
    }

    // DELETE DATA
    // ///////////////////////////////////////////////////////////////////////////////////

    @RequestMapping("/admin/delete-user/{id}")
    public String deleteUser(Model model, @PathVariable long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/delete-offer/{id}")
    public String deleteOffer(Model model, @PathVariable long id) {
        offerRepository.deleteById(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/delete-review/{id}")
    public String deleteReview(Model model, @PathVariable long id) {
        reviewRepository.deleteById(id);
        return "redirect:/admin";
    }

    @RequestMapping("/admin/delete-book/{id}")
    public String deleteBook(Model model, @PathVariable long id) {
        bookRepository.deleteById(id);
        return "redirect:/admin";
    }

}
