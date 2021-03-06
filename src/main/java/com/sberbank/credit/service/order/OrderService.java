package com.sberbank.credit.service.order;


import com.sberbank.credit.model.entity.OrderEntity;

import java.util.List;

public interface OrderService {

    OrderEntity getOrder(Long id);

    List<OrderEntity> findAll();

    OrderEntity createOrder(OrderEntity order);
}
