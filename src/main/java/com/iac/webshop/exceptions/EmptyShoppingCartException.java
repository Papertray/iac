package com.iac.webshop.exceptions;

public class EmptyShoppingCartException extends RuntimeException{
    public EmptyShoppingCartException(long finalOrderId) {
        super("Your shopping cart seems to be empty. "+  finalOrderId);
    }
}
