package com.restservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restservice.domain.User;
import com.restservice.repository.UserRespository;

@Service
public class UserService {

	@Autowired
	private UserRespository userRespository;

	public List<User> getAllUser() {
		return userRespository.findAll();
	}

	public User createUser(User user) {
		return userRespository.save(user);

	}

	public Optional<User> getuserById(Long id) {
		Optional<User> user = userRespository.findById(id);
		return user;
	}
	
	public User updateUser(Long id, User user) {
		user.setId(id);
		return userRespository.save(user);
	}

	public void deleteUserById(Long id) {
		if(userRespository.findById(id).isPresent()) {
			userRespository.deleteById(id);
		}
	}
	
	public User findByUserName(String userName) {
		return userRespository.findByUserName(userName);
	}
}
