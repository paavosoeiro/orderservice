package com.paavo.orderservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.paavo.orderservice.entity.Outbox;

public interface OutboxRepository extends CrudRepository<Outbox, Integer>{

}
