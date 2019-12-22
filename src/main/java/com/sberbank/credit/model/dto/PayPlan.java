package com.sberbank.credit.model.dto;

public class PayPlan {

    private Integer currentMonth;
    private Double currentRateSum;
    private Double currentSum;
    private Double sumLeft;

    public PayPlan() {
    }

    public PayPlan(Integer currentMonth, Double currentRateSum, Double currentSum, Double sumLeft) {
        this.currentMonth = currentMonth;
        this.currentRateSum = currentRateSum;
        this.currentSum = currentSum;
        this.sumLeft = sumLeft;
    }

    public Double getCurrentSum() {
        return currentSum;
    }

    public void setCurrentSum(Double currentSum) {
        this.currentSum = currentSum;
    }

    public Integer getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(Integer currentMonth) {
        this.currentMonth = currentMonth;
    }

    public Double getCurrentRateSum() {
        return currentRateSum;
    }

    public void setCurrentRateSum(Double currentRateSum) {
        this.currentRateSum = currentRateSum;
    }

    public Double getSumLeft() {
        return sumLeft;
    }

    public void setSumLeft(Double sumLeft) {
        this.sumLeft = sumLeft;
    }
}
