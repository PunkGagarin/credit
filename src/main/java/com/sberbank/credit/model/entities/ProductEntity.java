package com.sberbank.credit.model.entities;

import com.sberbank.credit.constants.CreditConstants;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Entity
@Table(schema = CreditConstants.CREDIT, name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    @Positive
    @Min(1L)
    private Long code;

    @Column(name = "minSum")
    private int minSum;
    @Column(name = "maxSum")
    private int maxSum;

    @Column(name = "minRate")
    private int minRate;
    @Column(name = "maxRate")
    private int maxRate;

    @Column(name = "term")
    private int term;

    public ProductEntity() {
    }

    public ProductEntity(int minSum, int maxSum, int minRate, int maxRate, int term) {
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
