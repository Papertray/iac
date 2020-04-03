package com.iac.webshop.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Category> categories;

    // Implement image storage
    private Long image;


    public Product() {
    }

}
