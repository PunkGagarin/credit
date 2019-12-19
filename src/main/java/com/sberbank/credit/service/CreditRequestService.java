package com.sberbank.credit.service;

import com.sberbank.credit.model.CreditInfo;
import com.sberbank.credit.model.CreditRequestEntity;
import com.sberbank.credit.model.OrderEntity;
import com.sberbank.credit.model.ProductEntity;

import java.util.List;

public interface CreditRequestService {

    List<CreditRequestEntity> findAll();

    CreditRequestEntity saveRequest(CreditRequestEntity creditRequest);

    CreditRequestEntity createRequestByOrderAndProduct(OrderEntity order, ProductEntity product);

    CreditRequestEntity findById(Long id);

    CreditInfo getCreditInfo(Long creditId) throws Exception;
}
