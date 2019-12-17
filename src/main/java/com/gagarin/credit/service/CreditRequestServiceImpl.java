package com.gagarin.credit.service;

import com.gagarin.credit.model.CreditRequestEntity;
import com.gagarin.credit.model.OrderEntity;
import com.gagarin.credit.model.ProductEntity;
import com.gagarin.credit.repository.CreditRequestRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Override
    public CreditRequestEntity createRequestByOrderAndProduct(OrderEntity order, ProductEntity product) {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setOrder(order);
        creditRequest.setCreateDate(new Date());
        creditRequest.setSum(order.getSum());
        creditRequest.setUserLogin(order.getUser().getLogin());
        creditRequest.setRate(findRateByOrder(order, product));

        return creditRequest;
    }

    private Double findRateByOrder(OrderEntity order, ProductEntity product) {
        int discount = order.getDiscount();
        double discountSum = order.getSum() -  (order.getSum() * discount / 100);
        double finalRate = 0;

        for (double currentRate = product.getMinRate(); currentRate <= product.getMaxRate(); currentRate += 0.1) {
            double sumBetween = order.getSum() - (discountSum * (currentRate / 100) + discountSum);
            if (sumBetween < 0)
                break;
            finalRate = round(currentRate);
        }
        return finalRate;
    }

    private double round(double number){
        double val = number*10;
        val = Math.round(val);
        return val/10;
    }
}
