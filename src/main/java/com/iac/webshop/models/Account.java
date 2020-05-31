package com.iac.webshop.models;

import com.iac.webshop.helpers.Utils;
import lombok.Data;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.util.Date;

@Data
@Entity
public class Account {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createdOn;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String hashedPassword;

    private String password;

    private boolean isActive;

    // Initializers

    public Account() {
        if (createdOn == null) createdOn = new Date();
    }

    // Methods

    public void setPassword(String password) {
        this.password = password;
        this.hashedPassword = Utils.hashPassword(password);
    }

    // Validation

    public void validate() {
        validateEmail();
        validatePassword();
    }

    public void validatePassword() throws ValidationException {
        if (password == null) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long");
        }
        if (hashedPassword == null) {
            throw new ValidationException("Password could not be hashed");
        }
    }

    public void validateEmail() throws ValidationException {
        if (!Utils.isValidEmail(email)) {
            throw new ValidationException("Email is invalid");
        }
    }
}