package com.iac.webshop.models;

import javax.persistence.*;
import javax.xml.bind.ValidationException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private BigDecimal minimumPrice = BigDecimal.ZERO;

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
        // Get discount price
        Date date = new Date();
        for (Discount discount : discounts) {
            if (date.after(discount.getStartDate()) || date.before(discount.getEndDate())) {
                return discount.getDiscountPrice();
            }
        }

        return price;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
