package es.codeurjc.readmebookstore.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.service.UserService;
import es.codeurjc.readmebookstore.service.MailService;
import javax.mail.MessagingException;

@Controller
public class LoginController {

	@Autowired
	private MailService mailService;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login(Model model, @RequestParam(required = false) String error) {
		model.addAttribute("loginerError", (error != null));
		return "login-page";
	}

	@GetMapping("/register")
	public String register(Model model) {
		return "register-page";
	}

	@PostMapping("/users")
	public String newUserProcess(Model model, User user, MultipartFile imageField) throws IOException {

		List<User> users = userService.findAll();

		// Checks if the user already exists:
		for (User u : users) {
			if (user.getName().equals(u.getName())) {
				model.addAttribute("registerError", true);
				return "register-page";
			}
		}

		// Saves the profile image if it has been upoaded:
		if (!imageField.isEmpty()) {
			user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			user.setImage(true);
		}

		// Saves the new user:
		user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
		userService.save(user);

		// Sends a coonfirmation mail:
		String subject = "Confirmación de registro";
		String body = "Hola " + user.getName() + ",";
		try {
			mailService.sendConfirmationEmail(user.getEmail(), subject, body);
		} catch (MessagingException e) {

			return "register-page";
		}

		return "login-page";
	}
}