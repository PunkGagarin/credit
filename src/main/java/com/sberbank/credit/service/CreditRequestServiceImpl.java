package com.sberbank.credit.service;

import com.sberbank.credit.model.CreditInfo;
import com.sberbank.credit.model.CreditRequestEntity;
import com.sberbank.credit.model.OrderEntity;
import com.sberbank.credit.model.ProductEntity;
import com.sberbank.credit.repository.CreditRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    public CreditRequestEntity saveRequest(CreditRequestEntity creditRequest) {
        return creditRequestRepository.save(creditRequest);
    }

    @Override
    public CreditRequestEntity createRequestByOrderAndProduct(OrderEntity order, ProductEntity product) {
        CreditRequestEntity creditRequest = new CreditRequestEntity();
        creditRequest.setOrder(order);
        creditRequest.setCreateDate(new Date());
        creditRequest.setRate(findRateByOrder(order, product));
        //TODO:Count Sum properly
        creditRequest.setSum((order.getSum() * (creditRequest.getRate() / 100)) + order.getSum());

        return creditRequest;
    }

    private Double findRateByOrder(OrderEntity order, ProductEntity product) {
        double discountSum = order.getSum() - (order.getSum() * order.getDiscount() / 100);
        double finalRate = 0;

        for (double currentRate = product.getMinRate(); currentRate <= product.getMaxRate(); currentRate += 0.1) {
            double currentSum = (discountSum * (currentRate / 100) / 12 * product.getTerm()) + discountSum;
            double sumBetween = order.getSum() - currentSum;
            if (sumBetween < 0)
                break;
            finalRate = round(currentRate);
        }
        return finalRate;
    }

    private double round(double number) {
        double val = number * 10;
        val = Math.round(val);
        return val / 10;
    }

    @Override
    public CreditRequestEntity findById(Long id) {
        return new CreditRequestEntity(new Date(), 10000.00, 5.0, "bla");
//        return creditRequestRepository.findById(id).orElse(null);
    }

    public CreditInfo getCreditInfo(Long creditId) throws Exception {
        CreditRequestEntity creditRequest = creditRequestRepository.findById(creditId).orElseThrow(Exception::new);
        return createCreditInfo(creditRequest);
    }

    private CreditInfo createCreditInfo(CreditRequestEntity creditRequest) {
        CreditInfo creditInfo = new CreditInfo();
        creditInfo.setCreateDate(creditRequest.getCreateDate());
        creditInfo.setCurrentDate(new Date());
        //TODO:
//        creditInfo.setMonthPast(getMonthPast(creditInfo.getCreateDate(), creditInfo.getCurrentDate()));
        creditInfo.setMonthPast(3);

        creditInfo.setTotalSum(creditRequest.getSum());
        creditInfo.setRate(creditRequest.getRate());
        creditInfo.setCurrentRateSum(getCurrentRateSum(creditInfo));
        //TODO:
        creditInfo.setSumLeft(creditInfo.getTotalSum());

        return creditInfo;
    }

    private double getSumLeft(CreditInfo creditInfo) {
        return 0;
    }

    private double getCurrentRateSum(CreditInfo creditInfo) {
        return creditInfo.getTotalSum() * (creditInfo.getRate() / 100) * creditInfo.getMonthPast();
    }


    private int getMonthPast(Date createDate, Date currentDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(createDate);
        int firstMonth = calendar.get(Calendar.MONTH);
        calendar.setTime(currentDate);
        return calendar.get(Calendar.MONTH) - firstMonth;
    }

//    private Date getCurrentDate(Date createDate) {
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(createDate);
//
//
//    }
}
