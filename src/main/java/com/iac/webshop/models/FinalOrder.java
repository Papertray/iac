package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Set<OrderLine> orderLines = new HashSet<>();

    private boolean finished;

    private BigDecimal getPrice() {
        BigDecimal allPrices = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLines) {
            allPrices = allPrices.add(orderLine.getTotalPrice());
        }
        return allPrices;
    }

    public boolean getStatus() {
        return finished;
    }

    public FinalOrder() {
    }

    public void removeOrderLine (OrderLine orderLine) {
        orderLines.remove(orderLine);
        orderLine.setFinalOrder(null);
    }
    public void addOrderLine (OrderLine orderLine) {
        orderLines.add(orderLine);
        orderLine.setFinalOrder(this);
    }

    @JsonManagedReference(value="finalOrder2OrderLine")
    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    @Override
    public String toString() {
        return "FinalOrder{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", finished=" + finished +
                '}';
    }
}
