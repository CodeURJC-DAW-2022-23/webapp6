package es.codeurjc.readmebookstore.controller;
import java.io.IOException;
import java.security.Principal;
import jakarta.servlet.http.HttpServletRequest;
import es.codeurjc.readmebookstore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.codeurjc.readmebookstore.service.UserService;

import static java.lang.System.out;


@Controller
public class UserWebController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
       

        if (principal != null) {
            User user = userService.findByName(principal.getName());

            model.addAttribute("logged", true);
            model.addAttribute("id", request.getRequestedSessionId());
            model.addAttribute("name", principal.getName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("admin", request.isUserInRole("ADMIN"));

        } else {
            model.addAttribute("logged", false);
        }
    }

    @GetMapping("/user/{id}")
    public String showUser(Model model, @PathVariable java.lang.String id) {

        //model.addAttribute("ga", gameService.findAll());

        return "user";
    }

    @PostMapping("/updateUser")
    public String updateUserProcess(Model model, User user, HttpServletRequest request) throws IOException {


        Principal principal = request.getUserPrincipal();
        String sessionName = principal.getName();
        User loggedUser = userService.findByName(sessionName);
        loggedUser.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));

        userService.save(loggedUser);

        return "redirect:/user/" + user.getId();
    }
    @GetMapping("/user-page.html")
	public String user(Model model) {
		return "user-page";
	}
	@RequestMapping("/user-page")
	public String user() {
		return "user-page.html";
	}
}
