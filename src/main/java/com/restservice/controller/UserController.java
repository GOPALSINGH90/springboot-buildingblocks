package com.restservice.controller;

import java.util.List;
import java.util.Optional;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservice.domain.User;
import com.restservice.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public List<User> getUsers() {
		return userService.getAllUser();
	}

	@PostMapping
	public User Createuser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/{id}")
	public Optional<User> getuserById(@PathVariable Long id) {
		Optional<User> user = userService.getuserById(id);
		return user;
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody  User user) {
		return userService.updateUser(id, user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}
	
	@GetMapping("/byusername/{userName}")
	public User getUserByName(@PathVariable String userName) {
		return userService.findByUserName(userName);
	}
}
