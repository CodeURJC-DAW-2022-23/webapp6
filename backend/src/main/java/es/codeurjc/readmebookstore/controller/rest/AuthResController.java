package es.codeurjc.readmebookstore.controller.rest;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.codeurjc.readmebookstore.security.jwt.AuthResponse;
import es.codeurjc.readmebookstore.security.jwt.LoginRequest;
import es.codeurjc.readmebookstore.security.jwt.UserLoginService;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import es.codeurjc.readmebookstore.security.jwt.AuthResponse.Status;
import es.codeurjc.readmebookstore.model.User;
import es.codeurjc.readmebookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@RequestMapping("/api/auth")
public class AuthResController {

	@Autowired
	private UserLoginService userLoginService;
    @Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Operation(summary = "Login a user")
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@CookieValue(name = "accessToken", required = false) String accessToken,
			@CookieValue(name = "refreshToken", required = false) String refreshToken,
			@RequestBody LoginRequest loginRequest) {
		
		return userLoginService.login(loginRequest, accessToken, refreshToken);
	}

    @Operation(summary = "Refresh the authenticated users")
	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(
			@CookieValue(name = "refreshToken", required = false) String refreshToken) {

		return userLoginService.refresh(refreshToken);
	}

    @Operation(summary = "Logout user")
	@PostMapping("/logout")
	public ResponseEntity<AuthResponse> logOut(HttpServletRequest request, HttpServletResponse response) {

		return ResponseEntity.ok(new AuthResponse(Status.SUCCESS, userLoginService.logout(request, response)));
	}

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user, HttpServletRequest request) {
        List<User> users = userService.findAll();

		// Checks if the user already exists:
		for (User u : users) {
			if (user.getName().equals(u.getName())) {				
				return new ResponseEntity<>("User is already taken!", HttpStatus.BAD_REQUEST);
			}
		}
		user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));		
		
		userService.save(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	}

}


/* PARA QUE SE REGISTRE CON LA CONTRASEÑA "pass"
@Operation(summary = "Register a new user")
    @PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user, HttpServletRequest request) {
        List<User> users = userService.findAll();

		// Checks if the user already exists:
		for (User u : users) {
			if (user.getName().equals(u.getName())) {				
				return new ResponseEntity<>("User is already taken!", HttpStatus.BAD_REQUEST);
			}
		}
		if(user.getEncodedPassword() == null) {
			user.setEncodedPassword("pass");
		}
		user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
		
		//user.setRoles("USER");
		userService.save(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
	} */