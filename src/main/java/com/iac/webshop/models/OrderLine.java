package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime date;

    private int amount;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private FinalOrder finalOrder;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Product product;

    public OrderLine() {
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(amount));
    }

    @JsonBackReference(value="product2OrderLine")
    public Product getProduct() {
        return product;
    }

    @JsonBackReference(value="finalOrder2OrderLine")
    public FinalOrder getFinalOrder() {
        return finalOrder;
    }

}
