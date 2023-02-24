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


	@RequestMapping("/loginerror-page")
	public String loginerror(Model model) {
		model.addAttribute("loginererror", true);

		return "login-page";
	}

	@RequestMapping("/logout")//Is not used I think
	public String logout(Model model) {

		model.addAttribute("loginerror", false);
		model.addAttribute("registererror", false);

		return "register";
	}

	

	@GetMapping("/register-page.html")
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
		for(User u : users) {
			if(user.getName().equals(u.getName())) {
				existe = true;
			}
		}
		if(existe) {

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

	@GetMapping("/user-page/{name}/image")
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
	
}