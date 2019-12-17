package com.gagarin.credit.service;

import com.gagarin.credit.model.CreditRequestEntity;

import java.util.List;

public interface CreditRequestService {

    List<CreditRequestEntity> findAll();

    CreditRequestEntity createRequest(CreditRequestEntity creditRequest);
}
