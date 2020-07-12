package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@Entity
@Table(name = "order_line")
public class OrderLine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime date;

    private int amount;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private FinalOrder finalOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @JsonBackReference(value="product2OrderLine")
    public Product getProduct() {
        return product;
    }

    @JsonBackReference(value="finalOrder2OrderLine")
    public FinalOrder getFinalOrder() {
        return finalOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLine )) return false;
        return Objects.equals(id, ((OrderLine) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }

    public void setTotalPrice(){
        this.totalPrice = product.getDiscountPrice().multiply(BigDecimal.valueOf(amount));
    }
}
