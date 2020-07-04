package com.iac.webshop.exceptions;

public class NotInStockException extends RuntimeException {

    public NotInStockException(int amount, int availableAmount, String productName) {
        super("You wanted " + amount + " times " + productName +  ", but there are only " + availableAmount + " left.");
    }
}