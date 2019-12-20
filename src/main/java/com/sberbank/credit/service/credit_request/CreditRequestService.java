package com.sberbank.credit.service.credit_request;

import com.sberbank.credit.model.dto.CreditInfo;
import com.sberbank.credit.model.entity.CreditRequestEntity;
import com.sberbank.credit.model.entity.OrderEntity;
import com.sberbank.credit.model.entity.ProductEntity;

import java.util.List;

public interface CreditRequestService {

    List<CreditRequestEntity> findAll();

    CreditRequestEntity saveRequest(CreditRequestEntity creditRequest);

    CreditRequestEntity createRequestByOrderAndProduct(OrderEntity order, ProductEntity product);

    CreditRequestEntity findById(Long id);

    CreditInfo getCreditInfo(Long creditId) throws Exception;
}
