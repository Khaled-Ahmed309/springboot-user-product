package com.example.product.product.exception;

public class ProductAlreadyExistingException extends RuntimeException {
    public ProductAlreadyExistingException(String name) {
        super("Product with name: "+name+" is already existing,Try again");
    }
}
