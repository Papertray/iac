package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "final_order")
public class FinalOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime date;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy="finalOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
            property  = "id")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Set<OrderLine> orderLines = new HashSet<>();

    private boolean finished;

    private BigDecimal getPrice() {
        BigDecimal allPrices = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLines) {
            allPrices = allPrices.add(orderLine.getTotalPrice());
        }
        totalPrice = allPrices;
        return totalPrice;
    }

    public FinalOrder() {
    }
}
