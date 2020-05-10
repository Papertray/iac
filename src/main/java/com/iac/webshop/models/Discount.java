package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private BigDecimal discountPrice;

    @Column(nullable = false)
    private Date startDate;

    private Date endDate;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Discount() {
    }

    @JsonBackReference(value="product2Discount")
    public Product getProduct() {
        return product;
    }
}
