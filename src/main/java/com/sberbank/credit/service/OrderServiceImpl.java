package com.sberbank.credit.service;

import com.sberbank.credit.model.OrderEntity;
import com.sberbank.credit.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderEntity getOrder(Long id) {
        if (!orderRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity createOrder(OrderEntity order) {
        return orderRepository.save(order);
    }
}
