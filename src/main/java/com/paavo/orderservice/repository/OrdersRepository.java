package com.paavo.orderservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.paavo.orderservice.entity.CustomerOrder;

public interface OrdersRepository extends CrudRepository<CustomerOrder, Integer> {

}
