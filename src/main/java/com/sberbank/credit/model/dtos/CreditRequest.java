package com.sberbank.credit.model.dtos;

import java.io.Serializable;
import java.util.Date;

public class CreditRequest implements Serializable {

    private Long id;

    private Date createDate;

    private Double sum;

    private Double rate;

    private Integer term;

    private String userLogin;

    public CreditRequest() {
    }

    public CreditRequest(Long id, Date createDate, Double sum, Double rate, Integer term, String userLogin) {
        this.id = id;
        this.createDate = createDate;
        this.sum = sum;
        this.rate = rate;
        this.term = term;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
