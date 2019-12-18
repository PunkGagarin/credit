package com.gagarin.credit.repository;

import com.gagarin.credit.config.AppConfig;
import com.gagarin.credit.model.OrderEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testOrderRepository(){
        orderRepository.findAll();
    }

    //TODO: user and userId
    @Test
    @Transactional
    public void testCreateOrder(){
        OrderEntity order = new OrderEntity(10000d, 10);
        order.setUser(userRepository.findAll().get(0));
        orderRepository.save(order);

        List<OrderEntity> orders = orderRepository.findAll();
        Assert.assertNotNull("Список не должен быть пустым", orders);
        Assert.assertTrue("Список не должен быть пустым", orders.size() > 0);

        orderRepository.delete(order);
    }
}
