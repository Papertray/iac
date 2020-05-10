package com.iac.webshop.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
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

    public Account() { if (createdOn == null) createdOn = new Date(); }

}
