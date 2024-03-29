package es.codeurjc.readmebookstore.controller.web;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;
import es.codeurjc.readmebookstore.service.ReviewService;

import javax.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.repository.UserRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            User user = userService.findByName(principal.getName());

            
            //List<Offer> historial = offerService.findShoppingHistorial(user.getId());

            model.addAttribute("logged", true);
            model.addAttribute("id", request.getRequestedSessionId());
            model.addAttribute("name", principal.getName());
            model.addAttribute("email", user.getEmail());

            //model.addAttribute("reviews", user.getReadedReviews());
            
            //model.addAttribute("historial", historial);
            model.addAttribute("hasImage", user.hasImage());
            if (user.hasImage()) {
                model.addAttribute("imageField", user.getImageFile());
            }
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/user")
    public String user(Model model, HttpServletRequest request,
            @RequestParam(defaultValue = "0") int currentFavoritesPage, @RequestParam(defaultValue = "0") int currentOffersPage, @RequestParam(defaultValue = "0") int currentReviewsPage, @RequestParam(defaultValue = "0") int currentHistoryPage) {

        User user = userService.findByName(request.getUserPrincipal().getName());
        
        model.addAttribute("favourites", bookService.favoriteBooks(user.getId(), currentFavoritesPage));
        model.addAttribute("offers", offerService.findOffersNotSoldByUser(user.getId(), currentOffersPage));
        model.addAttribute("reviews", reviewService.findAllReviewsByUser(user.getId(), currentReviewsPage));
        model.addAttribute("historial", offerService.findShoppingHistorial(user.getId(), currentHistoryPage));
        ;

        return "user-page";
    }

    @GetMapping("/updateProfile")
    public String updateUserProfile(Model model) {
        return "update-user-page";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(Model model, @RequestParam String email, HttpServletRequest request,
            MultipartFile imageField) throws IOException {
        String username = request.getUserPrincipal().getName();
        User user = userRepository.findByName(username).orElseThrow();
        user.setEmail(email);
        userService.save(user);
        try {
            userService.updateImage(user, false, imageField);
        } catch (Exception e) {
            return "redirect:/user";
        }
        userService.save(user);

        return "redirect:/user";
    }

    @PostMapping("/updateUser")
    public String updateUserProcess(Model model, User user, @RequestParam String email, HttpServletRequest request,
            MultipartFile imageField) throws IOException {

        Principal principal = request.getUserPrincipal();
        String sessionName = principal.getName();
        User loggedUser = userService.findByName(sessionName);
        loggedUser.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));

        userService.save(loggedUser);

        return "/user/";
    }

    @GetMapping("/users/{name}/image")
    public ResponseEntity<Object> downloadImage(@PathVariable String name) throws SQLException {

        User user = userService.findByName(name);
        if (user.getImageFile() != null) {

            Resource file = new InputStreamResource(user.getImageFile().getBinaryStream());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                    .contentLength(user.getImageFile().length()).body(file);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/user/favorites/{bookid}")
    public String addFavorite(Model model, @PathVariable long bookid, HttpServletRequest request) throws IOException {
        Book book = bookService.BookfindById(bookid);
        User user = userService.findByName(request.getUserPrincipal().getName());
        user.setFavouriteBooks(book);
        userService.save(user);
        return "redirect:/books/" + bookid;
    }

    @RequestMapping("/user/favorites/{bookid}/delete")
    public String removeFavorite(Model model, @PathVariable long bookid, HttpServletRequest request) {
        User user = userService.findByName(request.getUserPrincipal().getName());
        Book book = bookService.BookfindById(bookid);
        user.deleteFavouriteBooks(book);
        userService.save(user);
        return "redirect:"+ request.getHeader("Referer");
    }

}
