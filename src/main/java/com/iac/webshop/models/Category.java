package com.iac.webshop.models;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    // Implement image storage
    private Long image;

    public Category() {
    }
}
