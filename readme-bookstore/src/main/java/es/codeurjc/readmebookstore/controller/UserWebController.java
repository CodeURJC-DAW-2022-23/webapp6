package es.codeurjc.readmebookstore.controller;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.security.Principal;
import java.sql.SQLException;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import es.codeurjc.readmebookstore.repository.UserRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserWebController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
       

        if (principal != null) {
            User user = userService.findByName(principal.getName());

            model.addAttribute("logged", true);
            model.addAttribute("id", request.getRequestedSessionId());
            model.addAttribute("name", principal.getName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("favourites", user.getFavouriteBooks());
            model.addAttribute("reviews", user.getReadedReviews());
            model.addAttribute("offers", user.getOffers());
            model.addAttribute("hasImage", user.hasImage());
            if (user.hasImage()){
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
   

    /* @GetMapping("/user-page/{id}")
	public String User(Model model, @PathVariable long id) {

		Optional<User> user = userService.findById(id);
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
			return "user-page";
		} else {
			return "index-page";
		}
	} */

	@GetMapping("/updateProfile")
	public String updateUserProfile(Model model) {
		return "update-user-page";
	} 

    /* @RequestMapping("/updatedProfile")
	public String updatedProfile() {
		return "user-page.html";
	} */
    @PostMapping("/updateProfile")
	public String updateProfile(Model model,@RequestParam String email, HttpServletRequest request, MultipartFile imageField) throws IOException {
        String username = request.getUserPrincipal().getName();
		User user = userRepository.findByName(username).orElseThrow();
        user.setEmail(email);
        userService.save(user);
       try{
            updateImage(user, false, imageField);
            }catch(Exception e){
                return "user-page";
            }
        userService.save(user);

        


        return "index";
    }
    @PostMapping("/updateUser")
    public String updateUserProcess(Model model, User user, @RequestParam String email, HttpServletRequest request, MultipartFile imageField) throws IOException {


        Principal principal = request.getUserPrincipal();
        String sessionName = principal.getName();
        User loggedUser = userService.findByName(sessionName);
        loggedUser.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));

        userService.save(loggedUser);

        return "/user-page/";
    }

    private void updateImage(User user, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {

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

}
