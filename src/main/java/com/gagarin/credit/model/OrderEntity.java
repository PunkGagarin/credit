package com.gagarin.credit.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(schema = "credit2", name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
    @Column(name = "id")
    private Long id;


    @Positive
    @NotNull
    @Column(name = "sum")
    private double sum;


    @Column(name = "discount")
    private int discount;

    @OneToOne(mappedBy = "order")
    private BasketEntity basketEntity;

    @OneToOne(mappedBy = "order")
    private CreditRequestEntity creditRequest;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    public OrderEntity() {
    }

    public OrderEntity(@Positive @NotNull double sum, int discount) {
        this.sum = sum;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public BasketEntity getBasketEntity() {
        return basketEntity;
    }

    public void setBasketEntity(BasketEntity basketEntity) {
        this.basketEntity = basketEntity;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CreditRequestEntity getCreditRequest() {
        return creditRequest;
    }

    public void setCreditRequest(CreditRequestEntity creditRequest) {
        this.creditRequest = creditRequest;
    }
}
