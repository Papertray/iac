package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(exclude = {"finalOrder"})
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
    @JsonIgnore
//    @JoinColumn(name = "final_order_id")
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property  = "final_order_id")
    private FinalOrder finalOrder;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Product product;

    public OrderLine() {
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(BigDecimal.valueOf(amount));
    }

    public Product getProduct() {
        return product;
    }
}
