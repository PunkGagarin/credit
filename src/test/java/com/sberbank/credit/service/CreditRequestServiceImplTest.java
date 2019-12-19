package com.gagarin.credit.service;

import com.gagarin.credit.config.AppConfig;
import com.gagarin.credit.config.SecurityConfig;
import com.gagarin.credit.model.CreditInfo;
import com.gagarin.credit.model.CreditRequestEntity;
import com.gagarin.credit.model.OrderEntity;
import com.gagarin.credit.model.ProductEntity;
import com.gagarin.credit.repository.CreditRequestRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

//TODO: Import instead of Context, Mock or NGUnit
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class, SecurityConfig.class})
public class CreditRequestServiceImplTest {

    @Autowired
    private CreditRequestService creditRequestService;

//    @Mock
//    private CreditRequestRepository creditRequestRepository;

    @Autowired
    private CreditRequestRepository creditRequestRepository;

//    @Bean
//    @Primary
//    public CreditRequestRepository creditRequestRepository(){
//        return Mockito.mock(CreditRequestRepository.class);
//    }

    @Test
    @Transactional
    public void testCreateRequestByOrderAndProduct(){
        OrderEntity orderEntity = new OrderEntity(100000.00, 10,"some goods");
        ProductEntity productEntity = new ProductEntity(30000, 100000, 5, 24, 10);
        Double expectedRate = 13.3;

        CreditRequestEntity request =  creditRequestService.createRequestByOrderAndProduct(orderEntity, productEntity);

        Assert.assertEquals("Should be 13.3", expectedRate, request.getRate());

        creditRequestRepository.save(request);
    }

    @Test
    public void testCreateInfo() throws Exception {
        Long creditId = 1L;
        CreditRequestEntity request = new CreditRequestEntity(new Date(), 100000d, 13.3, "petrov");
        Mockito.when(creditRequestRepository.findById(creditId)).thenReturn(Optional.of(request));

        CreditInfo info = creditRequestService.getCreditInfo(creditId);
    }

}
