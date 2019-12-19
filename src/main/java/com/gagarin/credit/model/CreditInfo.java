package com.gagarin.credit.model;

import java.util.Date;

public class CreditInfo {

    private Date createDate;

    private Date currentDate;

    private int monthPast;

    private double rate;

    private double currentRateSum;

    private double totalSum;

    private double sumLeft;

    public CreditInfo() {
    }

    public CreditInfo(Date createDate, Date currentDate, int monthPast, double rate, double currentRateSum, double totalSum, double sumLeft) {
        this.createDate = createDate;
        this.currentDate = currentDate;
        this.monthPast = monthPast;
        this.rate = rate;
        this.currentRateSum = currentRateSum;
        this.totalSum = totalSum;
        this.sumLeft = sumLeft;
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

    public double getCurrentRateSum() {
        return currentRateSum;
    }

    public void setCurrentRateSum(double currentRateSum) {
        this.currentRateSum = currentRateSum;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public double getSumLeft() {
        return sumLeft;
    }

    public void setSumLeft(double sumLeft) {
        this.sumLeft = sumLeft;
    }

    public int getMonthPast() {
        return monthPast;
    }

    public void setMonthPast(int monthPast) {
        this.monthPast = monthPast;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
