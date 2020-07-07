package com.iac.webshop.exceptions;

public class OrderAlreadyBoughtException extends RuntimeException {

    public OrderAlreadyBoughtException(long finalOrderId) {
        super("Your order with id " + finalOrderId + " is already finished. ");
    }

}
