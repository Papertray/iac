package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iac.webshop.exceptions.EmptyShoppingCartException;
import com.iac.webshop.exceptions.NotInStockException;
import com.iac.webshop.exceptions.OrderAlreadyBoughtException;
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

    public void setTotalPrice(){
        BigDecimal allPrices = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLines) {
            allPrices = allPrices.add(orderLine.getTotalPrice());
        }
        this.totalPrice = allPrices;
    }

    public BigDecimal getTotalPrice(){
        return totalPrice;
    }

    public boolean getStatus() {
        return finished;
    }

    public FinalOrder() {
    }

    public void removeOrderLine (OrderLine orderLine) {
        orderLines.remove(orderLine);
        orderLine.setFinalOrder(null);
        setTotalPrice();
    }
    public void addOrderLine (OrderLine orderLine) {
        orderLines.add(orderLine);
        orderLine.setFinalOrder(this);
        setTotalPrice();
    }

    public void purchase() {
        if (getStatus()) throw new OrderAlreadyBoughtException(id);
        if  (orderLines.size() == 0) throw new EmptyShoppingCartException(id);

        for (OrderLine orderLine : orderLines) {
            if (orderLine.getProduct().getSupply() < orderLine.getAmount())
                throw new NotInStockException(orderLine.getAmount(), orderLine.getProduct().getSupply(), orderLine.getProduct().getName());
            orderLine.getProduct().reduceSupply(orderLine.getAmount());
        }
        setFinished(true);
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
                ", totalPrice=" + getTotalPrice() +
                ", finished=" + finished +
                '}';
    }
}
