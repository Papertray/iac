package com.iac.webshop.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity
public class FinalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    @Column(nullable = false)
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "finalOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines;

    private BigDecimal getPrice() {
        BigDecimal allPrices = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLines) {
            allPrices = allPrices.add(orderLine.getPrice());
        }
        totalPrice = allPrices;
        return totalPrice;
    }

    public FinalOrder() {
    }

}
