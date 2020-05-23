package com.iac.webshop.models;

import javax.persistence.*;
import javax.validation.ValidationException;
import java.util.Date;

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
    private String password;

    private boolean isActive;

    // Initializers

    public Account() { if (createdOn == null) createdOn = new Date(); }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Validation

    public void validate() {
        validateEmail();
    }

    private void validateEmail() throws ValidationException {

        if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
            throw new ValidationException("Email is invalid");
        }
    }
}
