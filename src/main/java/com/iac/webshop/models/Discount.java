package com.iac.webshop.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private double discountPrice;

    private Date endDate;

    private String description;

    public Discount() {
    }
}
