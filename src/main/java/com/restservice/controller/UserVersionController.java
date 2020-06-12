package com.restservice.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservice.domain.User;
import com.restservice.dtos.UserDtoV1;
import com.restservice.dtos.UserDtoV2;
import com.restservice.dtos.UserMmDto;
import com.restservice.exceptions.UserNotFoundException;
import com.restservice.services.UserService;

@RestController
@RequestMapping("versiong/users")
public class UserVersionController {
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/v1.0/{id}")
	public UserDtoV1 getuserById1(@PathVariable Long id) {
		Optional<User> userOptional = userService.getuserById(id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("not found");

		User user = userOptional.get();
		UserDtoV1 userDtoV1 = modelMapper.map(user, UserDtoV1.class);
		return userDtoV1;
	}

	@GetMapping("/v2.0/{id}")
	public UserDtoV2 getuserById2(@PathVariable Long id) {
		Optional<User> userOptional = userService.getuserById(id);
		if (!userOptional.isPresent())
			throw new UserNotFoundException("not found");

		User user = userOptional.get();
		UserDtoV2 userDtoV2 = modelMapper.map(user, UserDtoV2.class);
		return userDtoV2;
	}

}
