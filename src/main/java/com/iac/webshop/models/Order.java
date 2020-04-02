package com.iac.webshop.models;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Order() {
    }

}
