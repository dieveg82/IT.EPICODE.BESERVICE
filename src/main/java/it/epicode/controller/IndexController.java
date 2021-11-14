package it.epicode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.security.LoginRequest;
import it.epicode.security.SignupRequest;

@RestController
@RequestMapping("/indexcontroller")
public class IndexController {

	@Autowired
	AuthController authcontroller;

	@PostMapping("/signupform")
	public ResponseEntity<?> signUp(@RequestParam String username, String password, String nome, String cognome,
			String email) {

		SignupRequest signup = new SignupRequest(username, email, password, nome, cognome);
		return authcontroller.registerUser(signup);
		
	}
	
	@PostMapping("/loginform")
	public ResponseEntity<?> login(@RequestParam String username, String password) {

		LoginRequest login = new LoginRequest(username, password);
		return authcontroller.authenticateUser(login);
		
	}
	
}
