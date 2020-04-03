package com.iac.webshop.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    // Implement image storage
    private Long image;

    // INFO: Only add mappedBy on one side of the relation, use the attribute name not the table name
    // cascade = CascadeType.ALL
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Product> products;

    public Category() {
    }
}
