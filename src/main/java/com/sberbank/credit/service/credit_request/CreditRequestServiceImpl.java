package com.sberbank.credit.service.credit_request;

import com.sberbank.credit.model.dto.CreditInfo;
import com.sberbank.credit.model.dto.PayPlan;
import com.sberbank.credit.model.entity.CreditRequestEntity;
import com.sberbank.credit.model.entity.OrderEntity;
import com.sberbank.credit.model.entity.ProductEntity;
import com.sberbank.credit.repository.CreditRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
        return round((discountSum * (rate / 100) / 12 * product.getTerm()) + discountSum);
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
        creditInfo.setBaseSum(getDiscountSum(creditRequest.getOrder()));
        creditInfo.setCreateDate(creditRequest.getCreateDate());
        creditInfo.setCurrentDate(new Date());
        creditInfo.setTerm(creditRequest.getTerm());
        creditInfo.setTotalSum(creditRequest.getSum());
        creditInfo.setRate(creditRequest.getRate());
        creditInfo.setMonthPast(getMonthPast(creditInfo.getCreateDate(), creditInfo.getCurrentDate()));
        creditInfo.setCurrentRateSum(getCurrentRateSum(creditInfo, creditInfo.getMonthPast()));
        creditInfo.setSumLeft(getSumLeft(creditInfo, creditInfo.getMonthPast()));
        creditInfo.setNextMonths(countNextMonths(creditInfo));

        return creditInfo;
    }

    private List<PayPlan> countNextMonths(CreditInfo creditInfo) {
        List<PayPlan> nextMonths = new ArrayList<>();
        for (int i = 1; i <= creditInfo.getTerm() - creditInfo.getMonthPast(); i++) {
            int currentMonthPast = creditInfo.getMonthPast() + i;
            nextMonths.add(
                    new PayPlan(currentMonthPast,
                            round(getCurrentRateSum(creditInfo, currentMonthPast)),
                            getCurrentPayedSum(creditInfo, currentMonthPast),
                            getSumLeft(creditInfo, currentMonthPast),
                            getMonthName(creditInfo.getCreateDate(), currentMonthPast)
                    ));
        }
        return nextMonths;
    }

    private String getMonthName(Date createDate, int currentMonthPast) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(createDate);
        calendar.add(Calendar.MONTH, currentMonthPast);
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    }

    private Double getCurrentPayedSum(CreditInfo creditInfo, int currentMonthPast) {
        if (currentMonthPast == 0)
            return 0d;
        else
            return (creditInfo.getTotalSum() / creditInfo.getTerm()) * currentMonthPast;
    }

    private double getSumLeft(CreditInfo creditInfo, int monthPast) {
        if (monthPast == 0)
            return round(creditInfo.getTotalSum());
        else
            return round(creditInfo.getTotalSum() - (creditInfo.getTotalSum() / creditInfo.getTerm()) * monthPast);
    }

    private double getCurrentRateSum(CreditInfo creditInfo, int monthPast) {
        return (creditInfo.getTotalSum() - creditInfo.getBaseSum()) / creditInfo.getTerm() * monthPast;
    }

    private int getMonthPast(Date createDate, Date currentDate) {
        return (int) ChronoUnit.MONTHS.between(
                new java.sql.Date(createDate.getTime()).toLocalDate(),
                new java.sql.Date(new Date().getTime()).toLocalDate());
    }
}
