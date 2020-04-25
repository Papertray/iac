package com.iac.webshop.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String entity, Long id) {
        super("Could not find " + entity + " with id: " + id);
    }
}