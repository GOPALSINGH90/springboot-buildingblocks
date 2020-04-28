package com.restservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restservice.domain.Order;
import com.restservice.repository.OrderRespository;

@Service
public class OrderService {

	@Autowired
	private OrderRespository orderRepository;

	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> getOrders() {
		return  orderRepository.findAll();
	}

}
