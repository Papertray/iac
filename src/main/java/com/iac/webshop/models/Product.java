package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.xml.bind.ValidationException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private BigDecimal minimumPrice = BigDecimal.ZERO;

    private int supply;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    // Implement image storage
    private long image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<OrderLine> orderLines;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Discount> discounts;

    public Product() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Optional<BigDecimal> getDiscountPrice()  {
        if (discounts == null) {
            return Optional.empty();
        }
        // Get discount price
        Date date = new Date();
        for (Discount discount : discounts) {
            if (date.after(discount.getStartDate()) || date.before(discount.getEndDate())) {
                return Optional.of(discount.getDiscountPrice());
            }
        }
        return Optional.empty();
    }

    public void setPrice(BigDecimal price) throws ValidationException {
        if (price.scale() != 2) {
            throw new ValidationException("Two numbers after decimal expected");
        }
        if (price.compareTo(minimumPrice) < 0) {
            throw new ValidationException("Price lower than minimum price");
        }
        this.price = price;
    }

    public String getName() {
        return name;
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

    public long getImage() {
        return image;
    }

    public void setImage(long image) {
        this.image = image;
    }

    @JsonBackReference
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void copyFrom(Product product) {
        setName(product.getName());
        try {
            setPrice(product.getPrice());
        } catch (ValidationException e) {
            // TODO: Figure out how to pass this error to response body
            e.printStackTrace();
        }
        setDescription(product.getDescription());
        setImage(product.getImage());
    }
}
