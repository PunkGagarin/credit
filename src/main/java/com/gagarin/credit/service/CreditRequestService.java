package com.gagarin.credit.service;

import com.gagarin.credit.model.CreditInfo;
import com.gagarin.credit.model.CreditRequestEntity;
import com.gagarin.credit.model.OrderEntity;
import com.gagarin.credit.model.ProductEntity;

import java.util.List;

public interface CreditRequestService {

    List<CreditRequestEntity> findAll();

    CreditRequestEntity saveRequest(CreditRequestEntity creditRequest);

    CreditRequestEntity createRequestByOrderAndProduct(OrderEntity order, ProductEntity product);

    CreditRequestEntity findById(Long id);

    CreditInfo getCreditInfo(Long creditId) throws Exception;
}
