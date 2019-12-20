package com.sberbank.credit.service.credit_request;

import com.sberbank.credit.model.dto.CreditInfo;
import com.sberbank.credit.model.entity.CreditRequestEntity;
import com.sberbank.credit.model.entity.OrderEntity;
import com.sberbank.credit.model.entity.ProductEntity;
import com.sberbank.credit.repository.CreditRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service("creditRequest")
@Transactional
public class CreditRequestServiceImpl implements CreditRequestService {

    @Autowired
    private CreditRequestRepository creditRequestRepository;

    @Override
    public List<CreditRequestEntity> findAll() {
        return creditRequestRepository.findAll();
    }

    @Override
    public CreditRequestEntity saveRequest(CreditRequestEntity creditRequest) {
        return creditRequestRepository.save(creditRequest);
    }

    @Override
    public CreditRequestEntity findById(Long id) {
        return creditRequestRepository.findById(id).orElse(null);
    }

    @Override
    public CreditRequestEntity createRequestByOrderAndProduct(OrderEntity order, ProductEntity product) {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setOrder(order);
        creditRequest.setCreateDate(new Date());
        creditRequest.setRate(findRateByOrder(order, product));
        creditRequest.setSum(getCurrentSum(order, product, creditRequest.getRate()));
        creditRequest.setTerm(product.getTerm());

        return creditRequest;
    }

    private Double findRateByOrder(OrderEntity order, ProductEntity product) {
        double finalRate = 0;

        for (double currentRate = product.getMinRate(); currentRate <= product.getMaxRate(); currentRate += 0.1) {
            double currentSum = getCurrentSum(order, product, currentRate);
            double sumBetween = order.getSum() - currentSum;
            if (sumBetween < 0) {
                break;
            }
            finalRate = round(currentRate);
        }
        return finalRate;
    }

    private double getDiscountSum(OrderEntity order) {
        return order.getSum() - (order.getSum() * order.getDiscount() / 100);
    }

    private double getCurrentSum(OrderEntity order, ProductEntity product, double rate) {
        double discountSum = getDiscountSum(order);
        return (discountSum * (rate / 100) / 12 * product.getTerm()) + discountSum;
    }

    private double round(double number) {
        double val = number * 10;
        val = Math.round(val);
        return val / 10;
    }

    public CreditInfo getCreditInfo(Long creditId) throws Exception {
        CreditRequestEntity creditRequest = creditRequestRepository.findById(creditId).orElseThrow(Exception::new);
        return createCreditInfo(creditRequest);
    }

    private CreditInfo createCreditInfo(CreditRequestEntity creditRequest) {
        CreditInfo creditInfo = new CreditInfo();
        creditInfo.setCreateDate(creditRequest.getCreateDate());
        creditInfo.setCurrentDate(new Date());
        creditInfo.setTerm(creditRequest.getTerm());
        creditInfo.setTotalSum(creditRequest.getSum());
        creditInfo.setRate(creditRequest.getRate());
        creditInfo.setMonthPast(getMonthPast(creditInfo.getCreateDate(), creditInfo.getCurrentDate()));
        creditInfo.setCurrentRateSum(getCurrentRateSum(creditInfo));
        creditInfo.setSumLeft(getSumLeft(creditInfo));

        return creditInfo;
    }

    private double getSumLeft(CreditInfo creditInfo) {
        if (creditInfo.getMonthPast() == 0)
            return creditInfo.getTotalSum();
        else
            return creditInfo.getTotalSum() - (creditInfo.getTotalSum() / creditInfo.getTerm()) * creditInfo.getMonthPast();
    }

    private double getCurrentRateSum(CreditInfo creditInfo) {
        return (creditInfo.getTotalSum() * (creditInfo.getRate() / 100) / creditInfo.getTerm()) * creditInfo.getMonthPast();
    }

    private int getMonthPast(Date createDate, Date currentDate) {
        return (int) ChronoUnit.MONTHS.between(
                new java.sql.Date(createDate.getTime()).toLocalDate(),
                new java.sql.Date(new Date().getTime()).toLocalDate());
    }

    public CreditRequestRepository getCreditRequestRepository() {
        return creditRequestRepository;
    }

    public void setCreditRequestRepository(CreditRequestRepository creditRequestRepository) {
        this.creditRequestRepository = creditRequestRepository;
    }
}
