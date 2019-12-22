package com.sberbank.credit.model.dto;

public class PayPlan {

    private Integer currentMonth;
    private Double currentRateSum;
    private Double currentSum;
    private Double sumLeft;
    private String currentMonthName;

    public PayPlan() {
    }

    public PayPlan(Integer currentMonth, Double currentRateSum, Double currentSum, Double sumLeft, String currentMonthName) {
        this.currentMonth = currentMonth;
        this.currentRateSum = currentRateSum;
        this.currentSum = currentSum;
        this.sumLeft = sumLeft;
        this.currentMonthName = currentMonthName;
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

    public String getCurrentMonthName() {
        return currentMonthName;
    }

    public void setCurrentMonthName(String currentMonthName) {
        this.currentMonthName = currentMonthName;
    }
}
