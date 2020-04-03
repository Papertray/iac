package com.iac.webshop.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    private int amount;

    @Column(nullable = false)
    private double totalPrice;

    public OrderLine() {
    }

}
