package com.paavo.orderservice.service;

import java.time.LocalDateTime;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paavo.orderservice.entity.CustomerOrder;
import com.paavo.orderservice.entity.Outbox;
import com.paavo.orderservice.repository.OrdersRepository;
import com.paavo.orderservice.repository.OutboxRepository;

@Service
public class OrdersService {

	private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);
	
	@Autowired
	private OrdersRepository repository;

	@Autowired
	private OutboxRepository outboxRepository;

	@Transactional
	public Map<String, Object> createOrder(Map<String, Object> orderMap) {

		CustomerOrder order = new CustomerOrder();
		order.setName(orderMap.get("name").toString());
		order.setQuantity(Integer.parseInt(String.valueOf(orderMap.get("quantity"))));
		this.repository.save(order);

		Outbox outbox = new Outbox();

		outbox.setEvent("order_created");
		outbox.setEventId(order.getId());

		outbox.setPayload(orderMap);

		outbox.setCreatedAt(LocalDateTime.now());

		logger.info(outbox.toString());
		
		this.outboxRepository.save(outbox);
		
		this.outboxRepository.delete(outbox);
		
		
		return Map.of("orderId",order.getId());

	}

}
