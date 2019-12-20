package com.sberbank.credit.service.credit_request;

import com.sberbank.credit.model.dtos.CreditInfo;
import com.sberbank.credit.model.entities.CreditRequestEntity;
import com.sberbank.credit.model.entities.OrderEntity;
import com.sberbank.credit.model.entities.ProductEntity;

import java.util.List;

public interface CreditRequestService {

    List<CreditRequestEntity> findAll();

    CreditRequestEntity saveRequest(CreditRequestEntity creditRequest);

    CreditRequestEntity createRequestByOrderAndProduct(OrderEntity order, ProductEntity product);

    CreditRequestEntity findById(Long id);

    CreditInfo getCreditInfo(Long creditId) throws Exception;
}
