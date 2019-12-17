package com.gagarin.credit.service;

import com.gagarin.credit.config.AppConfig;
import com.gagarin.credit.model.CreditRequestEntity;
import com.gagarin.credit.model.OrderEntity;
import com.gagarin.credit.model.ProductEntity;
import com.gagarin.credit.model.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//TODO: Import instead of Context, Mock or NGUnit
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CreditRequestServiceImplTest {

    @Autowired
    private CreditRequestService creditRequestService;

    @Test
    public void testCreateRequestByOrderAndProduct(){
        OrderEntity orderEntity = new OrderEntity(100000, 10);
        orderEntity.setUser(new UserEntity("Tested Test Testedov", "tester", "123456"));
        ProductEntity productEntity = new ProductEntity(30000, 100000, 5, 24, 10);
        Double expectedRate = 11.1;

        CreditRequestEntity request =  creditRequestService.createRequestByOrderAndProduct(orderEntity, productEntity);

        Assert.assertEquals("Should be 11.1", expectedRate, request.getRate());
    }

}
