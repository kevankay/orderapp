package com.capgemini.orderapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	public int orderId;
	public String orderName;
	public String orderAddress;
	public double orderAmount;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderId, String orderName, String orderAddress, double orderAmount) {
		super();
		this.orderId = orderId;
		this.orderName = orderName;
		this.orderAddress = orderAddress;
		this.orderAmount = orderAmount;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderName=" + orderName + ", orderAddress=" + orderAddress
				+ ", orderAmount=" + orderAmount + "]";
	}
	
	

}
