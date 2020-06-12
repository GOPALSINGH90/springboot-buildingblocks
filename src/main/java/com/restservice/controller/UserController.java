package com.restservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.restservice.domain.User;
import com.restservice.exceptions.UserExistException;
import com.restservice.exceptions.UserNotFoundException;
import com.restservice.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "User managment services", value = "UserController")
@RestController
@RequestMapping("/users/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping()
	public List<User> getUsers() {
		return userService.getAllUser();
	}
	@ApiOperation(value = "create new User")
	@PostMapping
	public ResponseEntity<User> Createuser(@Valid @ApiParam("Api information for new user to be created") @RequestBody User user, UriComponentsBuilder builders) {
		User createdUser = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			createdUser = userService.createUser(user);
			headers.setLocation(builders.path("/users/{id}").buildAndExpand(user.getId()).toUri());
		} catch (UserExistException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		return new ResponseEntity<User>(createdUser, headers, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public User getuserById(@PathVariable Long id) {
		Optional<User> userOptional;
		try {
			userOptional = userService.getuserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
		return userOptional.get();
	}

	@PutMapping("/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody User user) {
		User user2;
		try {
			user2 = userService.updateUser(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		return user2;
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}

	@GetMapping("/byusername/{userName}")
	public User getUserByName(@PathVariable String userName) {
		User user =  userService.findByUserName(userName);
		if(user == null) {
			throw  new UserNotFoundException("user not found by this name ");
		}
		return user;
	}
}
