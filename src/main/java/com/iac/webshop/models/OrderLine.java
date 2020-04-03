package com.iac.webshop.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
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
}
