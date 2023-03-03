package es.codeurjc.readmebookstore.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.model.Book;
import es.codeurjc.readmebookstore.model.Offer;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.service.BookService;
import es.codeurjc.readmebookstore.service.OfferService;

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

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();

        if (principal != null) {
            User user = userService.findByName(principal.getName());

            List<Offer> offers = offerService.findOffersNotSoldByUser(user.getId());
            List<Offer> historial = offerService.findShoppingHistorial(user.getId());


            model.addAttribute("logged", true);
            model.addAttribute("id", request.getRequestedSessionId());
            model.addAttribute("name", principal.getName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("favourites", user.getFavouriteBooks());
            model.addAttribute("reviews", user.getReadedReviews());
            model.addAttribute("offers", offers);
            model.addAttribute("historial", historial);
            model.addAttribute("hasImage", user.hasImage());
            if (user.hasImage()) {
                model.addAttribute("imageField", user.getImageFile());
            }
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/user-page")
    public String user(Model model) {
        return "user-page";
    }

    @RequestMapping("/user-page")
    public String userLoad(Model model) {
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
            updateImage(user, false, imageField);
        } catch (Exception e) {
            return "user-page";
        }
        userService.save(user);

        return "user-page";
    }

    @PostMapping("/updateUser")
    public String updateUserProcess(Model model, User user, @RequestParam String email, HttpServletRequest request,
            MultipartFile imageField) throws IOException {

        Principal principal = request.getUserPrincipal();
        String sessionName = principal.getName();
        User loggedUser = userService.findByName(sessionName);
        loggedUser.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));

        userService.save(loggedUser);

        return "/user-page/";
    }

    private void updateImage(User user, boolean removeImage, MultipartFile imageField)
            throws IOException, SQLException {

        if (!imageField.isEmpty()) {
            user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
            user.setImage(true);
        } else {
            if (removeImage) {
                user.setImageFile(null);
                user.setImage(false);
            } else {
                // Maintain the same image loading it before updating the dish
                User dbUser = userService.findById(user.getId()).orElseThrow();
                if (dbUser.hasImage()) {
                    user.setImageFile(BlobProxy.generateProxy(dbUser.getImageFile().getBinaryStream(),
                            dbUser.getImageFile().length()));
                    user.setImage(true);
                }
            }
        }
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

    @GetMapping("/addfavorite/{bookid}")
	public String addFavorite(Model model, @PathVariable long bookid, HttpServletRequest request) throws IOException {
        Book book = bookService.BookfindById(bookid); 
        
        String sessionName = request.getUserPrincipal().getName();
        Optional<User> user = userService.findByNameopt(sessionName);
        user.get().setFavouriteBooks(book);
		userRepository.save(user.get());
        return "redirect:/book/" + bookid;
	}

    @GetMapping("/removefavorite/{bookid}")
	public String removeFavorite(Model model, @PathVariable long bookid) {
        userService.deletefavorite(bookid);
        return "redirect:/book/" + bookid;
	}

    @GetMapping("/delete-favourite-user-page/{bookid}")
    public String deleteFavoriteUserPage(Model model, @PathVariable long bookid) {
        userService.deletefavorite(bookid);
        return "redirect:/user-page";
	}
}
