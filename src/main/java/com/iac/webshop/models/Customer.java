package com.iac.webshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.iac.webshop.helpers.Utils;
import lombok.Data;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String email;

    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    public Customer() {
    }

    @JsonBackReference(value="address2Customer")
    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer )) return false;
        return Objects.equals(id, ((Customer) o).getId());
    }

    public void validate() {
        validateEmail();
        address.validate();
    }

    public void validateEmail() {
        if (!Utils.isValidEmail(email)) {
            throw new ValidationException("Email is invalid");
        }
    }



    @Override
    public int hashCode() {
        return 37;
    }
}
