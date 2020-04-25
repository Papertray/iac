package com.iac.webshop.exceptions;

public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException(Long id) {
        super("product", id);
    }
}