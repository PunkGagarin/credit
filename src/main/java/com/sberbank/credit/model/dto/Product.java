package com.sberbank.credit.model.dto;

import java.io.Serializable;

public class Product implements Serializable {

    private Long code;

    private int minSum;

    private int maxSum;

    private int minRate;

    private int maxRate;

    private int term;

    public Product() {
    }

    public Product(Long code, int minSum, int maxSum, int minRate, int maxRate, int term) {
        this.code = code;
        this.minSum = minSum;
        this.maxSum = maxSum;
        this.minRate = minRate;
        this.maxRate = maxRate;
        this.term = term;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public int getMinSum() {
        return minSum;
    }

    public void setMinSum(int minSum) {
        this.minSum = minSum;
    }

    public int getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(int maxSum) {
        this.maxSum = maxSum;
    }

    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(int maxRate) {
        this.maxRate = maxRate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
