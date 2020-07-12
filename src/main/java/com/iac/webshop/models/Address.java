package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "address")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Customer> customers;

    public Address() {
    }

    public void validate() {
        boolean[] validationRules = new boolean[]{
                (this.country.length() > 0),
                (this.city.length() > 0),
                (this.postalCode.length() > 0),
                (this.state.length() > 0),
                (this.street.length() > 0)
        };

        String[] validationMessages = new String[]{
                "Country is empty",
                "City is empty",
                "Postal code is empty",
                "State is empty",
                "Street is empty"
        };

        for (int i = 0; i < validationRules.length; i++) {
            if (!validationRules[i]) {
                throw new ValidationException(validationMessages[i]);
            }
        }
    }

    @JsonManagedReference(value = "address2Customer")
    public Set<Customer> getCustomers() {
        return customers;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", customers=" + customers +
                '}';
    }
}
