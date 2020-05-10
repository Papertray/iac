package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(schema = "public", name = "order_line")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private FinalOrder finalOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public OrderLine() {
    }

    public BigDecimal getPrice() {
        return product.getPrice().multiply(amount);
    }

    @JsonBackReference()
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonBackReference
    public FinalOrder getFinalOrder() {
        return finalOrder;
    }

    public void setFinalOrder(FinalOrder finalOrder) {
        this.finalOrder = finalOrder;
    }
}
