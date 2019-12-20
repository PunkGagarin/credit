package com.sberbank.credit.model.entities;


import com.sberbank.credit.config.constants.CreditConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(schema = CreditConstants.CREDIT, name = "orders")
public class OrderEntity {

    @Id
    @Positive
    @Column(name = "id")
    private Long id;


    @Positive
    @NotNull
    @Column(name = "sum")
    private Double sum;


    @Column(name = "discount")
    private Integer discount;

    @Column(name = "goods")
    private String goods;

    @OneToOne(mappedBy = "order")
    private CreditRequestEntity creditRequest;

    public OrderEntity() {
    }

    public OrderEntity(@Positive @NotNull Double sum, Integer discount, String goods) {
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

    public CreditRequestEntity getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequestEntity creditRequest) {
        this.creditRequest = creditRequest;
    }
}
