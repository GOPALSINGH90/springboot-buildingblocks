package com.restservice.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.restservice.domain.User;
import com.restservice.dtos.UserMmDto;
import com.restservice.exceptions.UserNotFoundException;
import com.restservice.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/{id}")
	public UserMmDto getuserById(@PathVariable Long id) {
		Optional<User> userOptional = userService.getuserById(id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("not found");

		User user = userOptional.get();
		UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
		return userMmDto;
	}

}
