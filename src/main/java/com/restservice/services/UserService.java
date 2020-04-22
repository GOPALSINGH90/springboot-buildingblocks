package com.restservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restservice.domain.User;
import com.restservice.exceptions.UserExistException;
import com.restservice.exceptions.UserNotFoundException;
import com.restservice.repository.UserRespository;

@Service
public class UserService {

	@Autowired
	private UserRespository userRespository;

	public List<User> getAllUser() {
		return userRespository.findAll();
	}

	public User createUser(User user) throws UserExistException{
		User existUser = userRespository.findByUserName(user.getUserName());
		if(existUser != null) {
			throw new UserExistException("user elready exist can't create with same user name");
		}
		return userRespository.save(user);

	}

	public Optional<User> getuserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRespository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("user not found in user repository");
		}
		return user;
	}

	public User updateUser(Long id, User user) throws UserNotFoundException {
		Optional<User> user1 = userRespository.findById(id);
		if (!user1.isPresent()) {
			throw new UserNotFoundException("User not found in user repository please provice valid user id");
		}
		user.setId(id);
		return userRespository.save(user);
	}

	public void deleteUserById(Long id) throws UserNotFoundException {
		if (!userRespository.findById(id).isPresent()) {
			throw new UserNotFoundException("User not found in user repository please provice valid user id");
		}
		userRespository.deleteById(id);
	}

	public User findByUserName(String userName) {
		return userRespository.findByUserName(userName);
	}
}
