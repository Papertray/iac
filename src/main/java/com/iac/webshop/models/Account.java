package com.iac.webshop.models;

import lombok.Data;
import com.iac.webshop.helpers.Utils;
import javax.persistence.*;
import javax.validation.ValidationException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Data
@Entity
@Table(schema = "public", name = "account")
public class Account {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date createdOn;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private byte[] hashedPassword;

    private String password;

    private boolean isActive;

    // Initializers

    public Account() {
        if (createdOn == null) createdOn = new Date();
    }

    // Methods

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
        try {
            this.hashedPassword = Utils.encrypt(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    // Validation

    public void validate() {
        validateEmail();
        validatePassword();
    }

    private void validatePassword() throws ValidationException {
        if (password == null) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters long");
        }
        if (hashedPassword == null) {
            throw new ValidationException("Something went wrong");
        }
    }

    private void validateEmail() throws ValidationException {
        if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            throw new ValidationException("Email is invalid");
        }
    }
}