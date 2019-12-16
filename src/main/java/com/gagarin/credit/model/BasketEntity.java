package com.gagarin.credit.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "credit2", name = "basket")
public class BasketEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    @ManyToMany
    @JoinTable(
            name = "basket_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "basket_id"))
    private Set<ProductEntity> basketProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Set<ProductEntity> getBasketProducts() {
        return basketProducts;
    }

    public void setBasketProducts(Set<ProductEntity> basketProducts) {
        this.basketProducts = basketProducts;
    }
}
