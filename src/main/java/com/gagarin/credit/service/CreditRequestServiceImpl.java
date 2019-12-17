package com.gagarin.credit.service;

import com.gagarin.credit.model.CreditRequestEntity;
import com.gagarin.credit.repository.CreditRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("creditRequest")
@Transactional
public class CreditRequestServiceImpl implements CreditRequestService {

    @Autowired
    CreditRequestRepository creditRequestRepository;

    @Override
    public List<CreditRequestEntity> findAll() {
        return creditRequestRepository.findAll();
    }

    @Override
    public CreditRequestEntity createRequest(CreditRequestEntity creditRequest) {
        return creditRequestRepository.save(creditRequest);
    }
}
