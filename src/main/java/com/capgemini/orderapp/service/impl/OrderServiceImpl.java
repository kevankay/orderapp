package com.capgemini.orderapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.exception.OrderIdAlreadyExistException;
import com.capgemini.orderapp.exception.OrderNotFoundException;
import com.capgemini.orderapp.repository.OrderRepository;
import com.capgemini.orderapp.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order addOrder(Order order) throws OrderIdAlreadyExistException {
		Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
		if (!orderFromDb.isPresent()) {
			return orderRepository.save(order);
		}
		throw new OrderIdAlreadyExistException("Order ID Already Exist");
	}
		

	@Override
	public Order editOrder(Order order) throws OrderNotFoundException {
		Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
		if (orderFromDb.isPresent()) {
			return orderRepository.save(order);
		}
		throw new OrderNotFoundException("Order not found");

	}
	
	@Override
	public List<Order> findAllOrders() {
		return (List<Order>) orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Order order) throws OrderNotFoundException {
		Optional<Order> cuOptional = orderRepository.findById(order.getOrderId());
		if (cuOptional.isPresent()) {
			orderRepository.delete(order);
			return;
		}
		throw new OrderNotFoundException("Order not found");
	}


	@Override
	public Order findOrderById(int orderId) throws OrderNotFoundException {
		Optional<Order> orderFromDb = orderRepository.findById(orderId);
		if (orderFromDb.isPresent()) {
			return orderFromDb.get();
		}
		throw new OrderNotFoundException("Order not found");
	}


	
}


