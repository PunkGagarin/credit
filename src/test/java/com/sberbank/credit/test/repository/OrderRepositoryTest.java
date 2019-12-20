package com.sberbank.credit.test.repository;

import com.sberbank.credit.config.AppConfig;
import com.sberbank.credit.model.entities.OrderEntity;
import com.sberbank.credit.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testOrderRepository() {
        orderRepository.findAll();
    }

    @Test
    public void testCreateOrder() {
        OrderEntity order = new OrderEntity(10000d, 10, "some goods");
        order.setId(106L);
        orderRepository.save(order);

        List<OrderEntity> orders = orderRepository.findAll();
        Assert.assertNotNull("Список не должен быть пустым", orders);
        Assert.assertTrue("Список не должен быть пустым", orders.size() > 0);

        orderRepository.delete(order);
    }
}
