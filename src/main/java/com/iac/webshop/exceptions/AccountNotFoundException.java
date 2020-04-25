package com.iac.webshop.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(long id) {
        super("Could not find account with id: " + id);
    }


}
