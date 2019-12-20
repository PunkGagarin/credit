package com.sberbank.credit.model.dtos;

import java.io.Serializable;

public class Order implements Serializable {

    private Long id;

    private Double sum;

    private Integer discount;

    private String goods;

    public Order() {
    }

    public Order(Long id, Double sum, Integer discount, String goods) {
        this.id = id;
        this.sum = sum;
        this.discount = discount;
        this.goods = goods;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}
