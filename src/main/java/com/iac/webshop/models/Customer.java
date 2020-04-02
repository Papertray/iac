package com.iac.webshop.models;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }


    // standard getters and setters
}
