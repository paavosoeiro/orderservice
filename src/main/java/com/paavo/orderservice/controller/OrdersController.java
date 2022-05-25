package com.paavo.orderservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paavo.orderservice.service.OrdersService;

@RestController
public class OrdersController {

	@Autowired
	private OrdersService service;

	public Map<String, Object> createOrder(@RequestBody Map<String, Object> order) {
		return this.service.createOrder(order);
	}
}
