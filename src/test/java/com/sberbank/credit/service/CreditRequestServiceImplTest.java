package com.sberbank.credit.service;

import com.sberbank.credit.config.TestConfig;
import com.sberbank.credit.model.CreditInfo;
import com.sberbank.credit.model.CreditRequestEntity;
import com.sberbank.credit.model.OrderEntity;
import com.sberbank.credit.model.ProductEntity;
import com.sberbank.credit.repository.CreditRequestRepository;
import com.sberbank.credit.service.credit_request.CreditRequestService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class CreditRequestServiceImplTest {

    @Autowired
    private CreditRequestService creditRequestService;

    @Autowired
    private CreditRequestRepository creditRequestRepository;

    private Long creditId;
    private CreditRequestEntity request;
    private OrderEntity orderEntity;
    private ProductEntity productEntity;

    @Before
    public void beforeClass() {
        creditId = 1L;
        request = new CreditRequestEntity(new Date(1571518348000L), 99975d,13.3 , 10, "tester");
        orderEntity = new OrderEntity(100000.00, 10, "some goods");
        productEntity = new ProductEntity(30000, 100000, 5, 24, 10);
        Mockito.when(creditRequestRepository.findById(creditId)).thenReturn(Optional.of(request));
    }


    @Test
    public void testCreateRequestByOrderAndProduct() {

        Double expectedRate = 13.3;
        CreditRequestEntity request = creditRequestService.createRequestByOrderAndProduct(orderEntity, productEntity);
        Assert.assertEquals("Should be 13.3", expectedRate, request.getRate());
    }

    @Test
    public void testCreateInfo() throws Exception {

        CreditInfo info = creditRequestService.getCreditInfo(creditId);

        Double expectedSumLeft = 79980d;
        Double expectedCurrentRateSum = 2659.335d;

        Assert.assertEquals("Must be " + request.getCreateDate(), request.getCreateDate(), info.getCreateDate());
        Assert.assertEquals("Must be " + request.getRate(), request.getRate(), info.getRate());
        Assert.assertEquals("Must be " + expectedSumLeft, expectedSumLeft, info.getSumLeft());
        Assert.assertEquals("Must be " + expectedCurrentRateSum, expectedCurrentRateSum, info.getCurrentRateSum());

    }
}
