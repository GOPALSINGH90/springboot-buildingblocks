package com.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restservice.domain.Order;

@Repository
public interface OrderRespository extends JpaRepository<Order, Long> {
}
