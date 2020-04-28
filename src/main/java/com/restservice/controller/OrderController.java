package com.restservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservice.domain.Order;
import com.restservice.domain.User;
import com.restservice.services.OrderService;
import com.restservice.services.UserService;

@RestController
@RequestMapping("users/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@PostMapping("/{userId}")
	public Order createOrder(@PathVariable long userId, @RequestBody Order order) {
		Optional<User> user = userService.getuserById(userId);
		if (user.isPresent()) {
			order.setUser(user.get());
		}

		Order createdOrder = orderService.createOrder(order);
		return createdOrder;
	}

	@GetMapping("/{userId}")
	public List<Order> getOrders(@PathVariable long userId) {
		Optional<User> users = userService.getuserById(userId);
		if(users.isPresent()) {
			
		}
		List<Order> orders = orderService.getOrders();
		return orders;
	}

}
