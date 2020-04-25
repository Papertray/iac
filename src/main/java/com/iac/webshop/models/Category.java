package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iac.webshop.helpers.ImageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Set;

@Entity
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Category implements Serializable {
    @Transient
    private ImageProvider imageProvider;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @Transient
    private String image;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> products;

    public String getName() {
        return name;
    }

    public Category() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BufferedImage getImage() {
        return imageProvider.ReadImage("Categories", String.valueOf(id));
    }

    public void setImage(String image) {
        imageProvider.decoder(image, "Category/"+String.valueOf(id));
    }

    @JsonManagedReference
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


}
