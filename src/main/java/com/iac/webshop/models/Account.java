package com.iac.webshop.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createdOn;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean isActive;

    public Account() {
    }
}
