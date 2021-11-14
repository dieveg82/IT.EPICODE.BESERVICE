package it.epicode.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.model.User;
import it.epicode.service.UserService;

@RestController
@RequestMapping ("/controlleruser")
public class UserController {

	@Autowired UserService userService;
	
	@GetMapping ("/findAll")
	public Page<User> findAll (Pageable page) {
		
		return userService.findAllPage (page);
	}
	
}
