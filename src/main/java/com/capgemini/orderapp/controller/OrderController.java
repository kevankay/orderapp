package com.capgemini.orderapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.exception.OrderIdAlreadyExistException;
import com.capgemini.orderapp.exception.OrderNotFoundException;
import com.capgemini.orderapp.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Order> addCustomer(@RequestBody Order order) throws OrderIdAlreadyExistException {
		return new ResponseEntity<Order>(orderService.addOrder(order), HttpStatus.OK);
	}

	
	@PutMapping("/order")
	public ResponseEntity<Order> editCustomer(@RequestBody Order order) throws OrderNotFoundException {
		return new ResponseEntity<Order>(orderService.editOrder(order), HttpStatus.OK);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable int orderId) throws OrderNotFoundException {
		return new ResponseEntity<Order>(orderService.findOrderById(orderId), HttpStatus.OK);
	}

	@DeleteMapping("/order/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable int orderId) throws OrderNotFoundException {
		Order order = orderService.findOrderById(orderId);
		orderService.deleteOrder(order);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/Orders")
	public @ResponseBody ResponseEntity<List<Order>> findAllOrders() {
		return new ResponseEntity<List<Order>>(orderService.findAllOrders(), HttpStatus.OK);
	}

}


