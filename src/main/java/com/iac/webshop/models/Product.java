package com.iac.webshop.models;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    private String description;

    // Implement one-to-many spring style
    // private List<???> categories;

    // Implement image storage
    private Long image;

    public Product() {
    }

}
