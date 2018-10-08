package com.capgemini.orderapp;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.orderapp.controller.OrderController;
import com.capgemini.orderapp.entity.Order;
import com.capgemini.orderapp.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderControllerTest {
	
	
		MockMvc mockMvc;

		@Mock
		OrderService orderService;

		@InjectMocks
		OrderController orderController;

		@Before
		public void setUp() {
			MockitoAnnotations.initMocks(this);
			mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
		}

		

		@Test
		public void testEditOrder() throws Exception {
			String content = "{\"orderId\": 1234," + "  \"orderName\": \"Keerthana\","
					+ "  \"orderAddress\": \"GNT\","
					+ "  \"orderAmount\": 1000" + "}";
			Order order = new Order(1234, "Keerthana", "GNT", 1000);
			when(orderService.editOrder(Mockito.isA(Order.class))).thenReturn(order);
			mockMvc.perform(put("/order").content(content).contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(jsonPath("$.orderId").exists())
					.andExpect(jsonPath("$.orderAddress").value("GNT"));
		}

		@Test
		public void testGetOrder() throws Exception {
			String content = "{" + "  \"orderId\": 1234," + "  \"orderName\": \"Keerthana\","
					 + "  \"orderAddress\": \"GNT\","
					+ "  \"orderAmount\": 1000" + "}";
			Order order = new Order(1234, "Keerthana", "GNT", 1000);
			when(orderService.findOrderById(1234)).thenReturn(order);
			mockMvc.perform(
					get("/order/1234").contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(jsonPath("$.orderId").exists())
					.andExpect(jsonPath("$.orderName").value("Keerthana"));
		}

		@Test
		public void testDeleteCustomer() throws Exception {
			Order order = new Order(1234, "Keerthana",  "GNT", 1000);
			when(orderService.findOrderById(1234)).thenReturn(order);
			mockMvc.perform(delete("/order/1234").contentType(MediaType.APPLICATION_JSON_UTF8)
					.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
		}



}
