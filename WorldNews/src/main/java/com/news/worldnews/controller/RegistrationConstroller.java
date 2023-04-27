package com.news.worldnews.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.worldnews.entity.Login;
import com.news.worldnews.entity.RegistrationRequest;
import com.news.worldnews.service.RegistrationService;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "api/v1/registration")
public class RegistrationConstroller {

	@Autowired
	private RegistrationService registrationService;

	@PostMapping()
	public String register(@RequestBody RegistrationRequest request) throws Exception {
		return registrationService.register(request);
	}

	@GetMapping(path = "confirm")
	public String confirm(@RequestParam("token") String token) {
//		System.out.println("Token in controller : "+token);
		return registrationService.confirmToken(token);
	
	}
	
	
	@PostMapping("/login")
	public boolean loginUser(@RequestBody Login login) throws NotFoundException {
		System.out.println("Login"+login.getUsername());
		return registrationService.loginUser(login);
	}

	@GetMapping("/hello")
	public String gethello() {
		return "Hello";
	}
}
