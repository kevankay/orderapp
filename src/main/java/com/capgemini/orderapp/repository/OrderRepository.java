package com.capgemini.orderapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.orderapp.entity.Order;

public interface OrderRepository  extends CrudRepository<Order, Integer>{

}
