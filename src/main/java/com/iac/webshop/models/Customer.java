package com.iac.webshop.models;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String email;

    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;

    public Customer() {
    }
}
