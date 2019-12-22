package com.sberbank.credit.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CreditInfo implements Serializable {

    private Date createDate;

    private Date currentDate;

    private Integer monthPast;

    private Double rate;

    private Double currentRateSum;

    private Double totalSum;

    private Double sumLeft;

    private Double baseSum;

    private Integer term;


    private List<PayPlan> nextMonths;

    public CreditInfo() {
    }

    public CreditInfo(Date createDate, Date currentDate, Integer monthPast, Double rate, Double currentRateSum, Double totalSum, Double sumLeft, Integer term, List<PayPlan> nextMonths) {
        this.createDate = createDate;
        this.currentDate = currentDate;
        this.monthPast = monthPast;
        this.rate = rate;
        this.currentRateSum = currentRateSum;
        this.totalSum = totalSum;
        this.sumLeft = sumLeft;
        this.term = term;
        this.nextMonths = nextMonths;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Integer getMonthPast() {
        return monthPast;
    }

    public void setMonthPast(Integer monthPast) {
        this.monthPast = monthPast;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getCurrentRateSum() {
        return currentRateSum;
    }

    public void setCurrentRateSum(Double currentRateSum) {
        this.currentRateSum = currentRateSum;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public Double getSumLeft() {
        return sumLeft;
    }

    public void setSumLeft(Double sumLeft) {
        this.sumLeft = sumLeft;
    }

    public Double getBaseSum() {
        return baseSum;
    }

    public void setBaseSum(Double baseSum) {
        this.baseSum = baseSum;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public List<PayPlan> getNextMonths() {
        return nextMonths;
    }

    public void setNextMonths(List<PayPlan> nextMonths) {
        this.nextMonths = nextMonths;
    }
}
