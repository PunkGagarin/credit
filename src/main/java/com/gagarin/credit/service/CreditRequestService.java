package com.gagarin.credit.service;

import com.gagarin.credit.model.CreditRequestEntity;
import com.gagarin.credit.model.OrderEntity;
import com.gagarin.credit.model.ProductEntity;

import java.util.List;

public interface CreditRequestService {

    List<CreditRequestEntity> findAll();

    CreditRequestEntity createRequest(CreditRequestEntity creditRequest);

    CreditRequestEntity createRequestByOrderAndProduct(OrderEntity order, ProductEntity product);
}
