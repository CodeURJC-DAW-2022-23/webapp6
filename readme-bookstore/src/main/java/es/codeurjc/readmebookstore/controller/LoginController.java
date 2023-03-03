package es.codeurjc.readmebookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String login(Model model) {
		return "login-page";
	}

	@RequestMapping("/loginerror-page")
	public String loginerror(Model model) {
		model.addAttribute("loginererror", true);

		return "login-page";
	}

	@RequestMapping("/logout") // Is not used I think
	public String logout(Model model) {

		model.addAttribute("loginerror", false);
		model.addAttribute("registererror", false);

		return "register";
	}

	@GetMapping("/register")
	public String register(Model model) {
		return "register-page";
	}

	@GetMapping("/newUser")
	public String newUser(Model model) {

		model.addAttribute("loginerror", false);
		model.addAttribute("registererror", false);

		return "register";
	}

	
	@PostMapping("/newUser")
	public String newUserProcess(Model model, User user, MultipartFile imageField) throws IOException {

		model.addAttribute("loginerror", false);

		boolean existe = false;
		List<User> users = userService.findAll();
		for (User u : users) {
			if (user.getName().equals(u.getName())) {
				existe = true;
			}
		}
		if (existe) {

			model.addAttribute("registererror", true);
			return "register-error";
		}

		if (!imageField.isEmpty()) {
			user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			user.setImage(true);
		}

		user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
		userService.save(user);

		model.addAttribute("registererror", false);

		String subject = "Confirmación de registro";
        String body = "Hola " + user.getName() + ",\n\nSomos Readme. Gracias por registrarte en nuestra página web.";

		try{
			mailService.sendConfirmationEmail(user.getEmail(), subject, body);
		}catch(MessagingException e){
			System.out.println("El error de senConfitmationEmail es: " + e);
			return "register-error";
		}

		return "register-success";
	}

	@RequestMapping("/register-success")
	public String showDishesSuccess(Model model) {

		model.addAttribute("registererror", false);

		return "login-page";
	}

	@RequestMapping("/register-error")
	public String showDishes(Model model) {

		model.addAttribute("registererror", true);

		return "register-page";
	}
}