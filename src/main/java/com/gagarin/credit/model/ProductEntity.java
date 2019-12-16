package com.gagarin.credit.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.Set;

@Entity
@Table(schema = "credit2", name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    @Positive
    @Min(1L)
    private Long code;

    private int minSum;
    private int maxSum;

    private int minRate;
    private int maxRate;

    private int term;

    @ManyToMany(mappedBy = "basketProducts")
    private Set<BasketEntity> baskets;

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

    public Set<BasketEntity> getBaskets() {
        return baskets;
    }

    public void setBaskets(Set<BasketEntity> baskets) {
        this.baskets = baskets;
    }
}
