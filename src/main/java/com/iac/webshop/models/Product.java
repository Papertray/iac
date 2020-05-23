package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.Set;


@Data
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
    @OneToOne()
    private File image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Category category;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<OrderLine> orderLines;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Discount> discounts;

    public Product() {
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
    @JsonManagedReference(value="product2OrderLine")
    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }

    @JsonManagedReference(value="product2Discount")
    public Set<Discount> getDiscounts() {
        return discounts;
    }

    @JsonBackReference(value="product2Category")
    public Category getCategory() {
        return category;
    }

    public void copyFrom(Product product) {
        setName(product.getName());
        setPrice(product.getPrice());
        setDescription(product.getDescription());
        setImage(product.getImage());
    }

    // Validation

    public void validate() {
        validateName();
        validatePrice();
    }

    public void validateName() throws ValidationException {

        if (name.isEmpty()) {
            throw new ValidationException("Name can not be empty");
        }
    }

    public void validatePrice() throws ValidationException {

        if (price.scale() != 2) {
            throw new ValidationException("Price must have two decimals");
        }

        if (price.compareTo(minimumPrice) < 0) {
            throw new ValidationException("Price was lower than minimum price");
        }
    }
}
