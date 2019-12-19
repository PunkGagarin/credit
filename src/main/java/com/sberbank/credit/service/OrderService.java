package com.gagarin.credit.service;

import com.gagarin.credit.model.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderEntity getOrder(Long id);

    List<OrderEntity> findAll();

    OrderEntity createOrder(OrderEntity order);
}
