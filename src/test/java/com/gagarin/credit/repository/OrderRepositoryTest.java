package com.gagarin.credit.repository;

import com.gagarin.credit.config.AppConfig;
import com.gagarin.credit.model.OrderEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Transactional
    public void testOrderRepository(){
        orderRepository.findAll();
    }
}
