package com.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restservice.domain.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}
