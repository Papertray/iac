package com.iac.webshop.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Customer> customers;

    public Address() {
    }
}
