package com.capgemini.orderapp.service;

import java.util.List;


import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.exception.OrderIdAlreadyExistException;
import com.capgemini.orderapp.exception.OrderNotFoundException;

public interface OrderService {
	public Order addOrder(Order order) throws OrderIdAlreadyExistException;
	public Order editOrder(Order order) throws OrderNotFoundException;
	public Order findOrderById(int orderId) throws OrderNotFoundException;
	public List<Order> findAllOrders();
	public void deleteOrder(Order order) throws OrderNotFoundException;
	
}
