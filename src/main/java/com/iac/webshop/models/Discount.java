package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private BigDecimal discountPrice;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Discount() {
    }
    @JsonBackReference(value="product2Discount")
    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", discountPrice=" + discountPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", product=" + product +
                '}';
    }
}
