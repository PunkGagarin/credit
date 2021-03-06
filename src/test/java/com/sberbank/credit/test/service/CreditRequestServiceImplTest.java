package com.sberbank.credit.test.service;

import com.sberbank.credit.model.dto.CreditInfo;
import com.sberbank.credit.model.dto.PayPlan;
import com.sberbank.credit.model.entity.CreditRequestEntity;
import com.sberbank.credit.model.entity.OrderEntity;
import com.sberbank.credit.model.entity.ProductEntity;
import com.sberbank.credit.repository.CreditRequestRepository;
import com.sberbank.credit.service.credit_request.CreditRequestService;
import com.sberbank.credit.test.config.TestConfig;
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
        orderEntity = new OrderEntity(100000.00, 10, "some goods");
        productEntity = new ProductEntity(30000, 100000, 5, 24, 10);
        request = new CreditRequestEntity(new Date(1571518348000L), 99975d, 13.3, 10, "tester");
        request.setOrder(orderEntity);
        Mockito.when(creditRequestRepository.findById(creditId)).thenReturn(Optional.of(request));
    }


    @Test
    public void testCreateRequestByOrderAndProduct() {

        Double expectedRate = 13.3;
        Integer expectedTerm = 10;
        CreditRequestEntity request = creditRequestService.createRequestByOrderAndProduct(orderEntity, productEntity);
        Assert.assertEquals("Must be " + expectedRate, expectedRate, request.getRate());
        Assert.assertEquals("Must be " + expectedTerm, expectedTerm, request.getTerm());

    }

    @Test
    public void testCreateInfo() throws Exception {

        CreditInfo info = creditRequestService.getCreditInfo(creditId);

        Double expectedSumLeft = 79980d;
        Double expectedCurrentRateSum = 1995.0d;

        Assert.assertEquals("Must be " + request.getCreateDate(), request.getCreateDate(), info.getCreateDate());
        Assert.assertEquals("Must be " + request.getRate(), request.getRate(), info.getRate());
        Assert.assertEquals("Must be " + expectedSumLeft, expectedSumLeft, info.getSumLeft());
        Assert.assertEquals("Must be " + expectedCurrentRateSum, expectedCurrentRateSum, info.getCurrentRateSum());
        Assert.assertEquals("Must be 8 months", 8, info.getNextMonths().size());

    }
}
