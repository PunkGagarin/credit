package com.gagarin.credit.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(schema = "credit2", name = "credit_request")
public class CreditRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Positive
    @Min(1L)
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    private Double sum;

    private int rate;

    @Column(name = "userLogin")
    private String userLogin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    public CreditRequestEntity() {
    }

    public CreditRequestEntity(Date createDate, Double sum, int rate, String userLogin) {
        this.createDate = createDate;
        this.sum = sum;
        this.rate = rate;
        this.userLogin = userLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
