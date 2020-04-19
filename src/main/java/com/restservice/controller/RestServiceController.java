package com.restservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservice.domain.User;

@RestController
@RequestMapping("/restapi")
public class RestServiceController {

	@GetMapping("/")
	public String getHelloWorld() {
		return "Hello World";
	}

	@GetMapping("/Userdetail")
	public User getUserDetails() {
		return new User("Gopal", "Singh", "Bangalore");
	}
}
