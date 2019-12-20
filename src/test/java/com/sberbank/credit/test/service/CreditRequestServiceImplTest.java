package com.sberbank.credit.test.service;

import com.sberbank.credit.model.dtos.CreditInfo;
import com.sberbank.credit.model.dtos.converters.Converter;
import com.sberbank.credit.model.dtos.converters.OrderConverter;
import com.sberbank.credit.model.entities.CreditRequestEntity;
import com.sberbank.credit.model.entities.OrderEntity;
import com.sberbank.credit.model.entities.ProductEntity;
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

    Converter orderConverter = new OrderConverter();

    private Long creditId;
    private CreditRequestEntity request;
    private OrderEntity orderEntity;
    private ProductEntity productEntity;

    @Before
    public void beforeClass() {
        creditId = 1L;
        request = new CreditRequestEntity(new Date(1571518348000L), 99975d, 13.3, 10, "tester");
        orderEntity = new OrderEntity(100000.00, 10, "some goods");
        productEntity = new ProductEntity(30000, 100000, 5, 24, 10);
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
        Double expectedCurrentRateSum = 2659.335d;

        Assert.assertEquals("Must be " + request.getCreateDate(), request.getCreateDate(), info.getCreateDate());
        Assert.assertEquals("Must be " + request.getRate(), request.getRate(), info.getRate());
        Assert.assertEquals("Must be " + expectedSumLeft, expectedSumLeft, info.getSumLeft());
        Assert.assertEquals("Must be " + expectedCurrentRateSum, expectedCurrentRateSum, info.getCurrentRateSum());

    }
}
