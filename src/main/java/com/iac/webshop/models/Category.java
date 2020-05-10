package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "public", name = "category")
// @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    private File image;

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

//    @Transient
//    private ImageProvider imageProvider;
//
//    public void setImage(String image) {
//        imageProvider.decoder(image, "Category/"+String.valueOf(id));
//    }
//
//    public BufferedImage getImage() {
//        return imageProvider.ReadImage("Categories", String.valueOf(id));
//    }

    @JsonManagedReference
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
