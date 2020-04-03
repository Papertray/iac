package com.iac.webshop.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class FinalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @Column(nullable = false)
    private double totalPrice;

    public FinalOrder() {
    }

}
