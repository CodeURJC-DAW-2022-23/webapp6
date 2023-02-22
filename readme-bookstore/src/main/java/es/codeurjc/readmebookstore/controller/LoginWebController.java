package es.codeurjc.readmebookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.service.UserService;

@Controller
public class LoginWebController {

	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping("/login")
	public String login() {
		return "login-page";
	}
	@GetMapping("/login-page.html")
	public String login(Model model) {
		return "login-page";
	}

	@GetMapping("/register-page.html")
	public String register(Model model) {
		return "register-page";
	}

	/* @GetMapping("/register")
	public String showDishes(Model model) {

		model.addAttribute("loginerror", false);

		return "register-page";
	} */


	@RequestMapping("/loginerror")
	public String loginerror(Model model) {

		model.addAttribute("loginerror", true);
		model.addAttribute("registererror", false);

		return "register-page";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {

		model.addAttribute("loginerror", false);
		model.addAttribute("registererror", false);

		return "register";
	}

	@GetMapping("/newuser")
	public String newUser(Model model) {

		model.addAttribute("loginerror", false);
		model.addAttribute("registererror", false);

		return "register";
	}

	@PostMapping("/newuser")
	public String newUserProcess(Model model, User user) throws IOException {

		model.addAttribute("loginerror", false);

		boolean existe = false;
		List<User> users = userService.findAll();
		for(User u : users) {
			if(user.getName().equals(u.getName())) {
				existe = true;
			}
		}
		if(existe) {

			model.addAttribute("registererror", true);
			return "register";
		}

		user.setImage(false);

		user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
		userService.save(user);

		model.addAttribute("registererror", false);

		return "register";
	}



	@GetMapping("/user/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<User> user = userService.findById(id);
		if (user.isPresent() && user.get().getImageFile() != null) {

			Resource file = new InputStreamResource(user.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(user.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
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