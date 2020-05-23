package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class FinalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDateTime date;

    /*
    public void setDate(String date) {
        String test = "2020-04-25 15:04:45";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime testDate = LocalDateTime.parse(test, formatter);
        LocalDateTime actualDate = LocalDateTime.parse(date, formatter);
        this.date = actualDate;
    }
    */

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "finalOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private Set<OrderLine> orderLines;

    private boolean finished;

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

    @JsonManagedReference(value = "finalOrder2OrderLine")
    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

}
