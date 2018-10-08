package com.capgemini.orderapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.repository.OrderRepository;
import com.capgemini.orderapp.service.impl.OrderServiceImpl;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderServiceTest {
	
	
		@Mock
		OrderRepository orderRepository;

		@InjectMocks
		OrderServiceImpl orderService;

		@org.junit.Before
		public void setUp() {
			MockitoAnnotations.initMocks(this);
		}

		
		@Test
		public void testEditCustomer() throws Exception {
			Order order = new Order(1234, "Keerthana",  "GNT", 1000);
			when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
			when(orderRepository.save(order)).thenReturn(order);
			assertEquals(orderService.editOrder(order), order);

		}

		@Test
		public void testGetOrder() throws Exception {
			Order order = new Order(1234, "Keerthana", "GNT", 1000);
			when(orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));
			assertEquals(orderService.findOrderById(1234), order);
		}

		@Test
		public void testGetAllCustomer() throws Exception {
			List<Order> orders = new ArrayList<>();
			Order order = new Order(1234, "Keerthana", "GNT",1000);
			orders.add(order);
			when(orderRepository.findAll()).thenReturn(orders);
			assertEquals(orderService.findAllOrders(), orders);
		}

		@Test
		public void testDeleteOrder() throws Exception {
			Order order = new Order(1234, "Keerthana",  "GNT", 1000);
			when(orderRepository.findById(1234)).thenReturn(Optional.of(order));
			orderService.deleteOrder(order);
			verify(orderRepository, times(1)).delete(order);
		}

		@Test
		public void testAddOrder() throws Exception {
			Order order = new Order(1234, "Keerthana", "GNT", 1000);
			when(orderRepository.save(order)).thenReturn(order);
			assertEquals(orderService.addOrder(order), order);
		}

	}



