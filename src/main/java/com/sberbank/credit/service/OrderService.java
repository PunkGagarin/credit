package com.sberbank.credit.service;

import com.sberbank.credit.model.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderEntity getOrder(Long id);

    List<OrderEntity> findAll();

    OrderEntity createOrder(OrderEntity order);
}
