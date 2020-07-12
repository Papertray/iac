package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
  
    @OneToOne(cascade = {CascadeType.ALL})
    private File image;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<OrderLine> orderLines;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL})
    private Set<Discount> discounts;

    public Product() {
    }

    public BigDecimal getDiscountPrice()  {
        // Get discount price
        LocalDateTime date = LocalDateTime.now();
        if (discounts != null) {
            for (Discount discount : discounts) {
                if (date.isAfter(discount.getStartDate()) || date.isBefore(discount.getEndDate())) {
                    return discount.getDiscountPrice();
                }
            }
        }
        return price;
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
        validateSupply();
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

    public void validateSupply() throws ValidationException {
        if (supply < 0) {
            throw new ValidationException("Supply can not be negative");
        }
    }


    public void reduceSupply(int amount){
        this.supply = supply - amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product )) return false;
        return Objects.equals(id, ((Product) o).getId());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", minimumPrice=" + minimumPrice +
                ", supply=" + supply +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
