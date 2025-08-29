package com.example.product.product.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String name) {
        super("Product with name: "+name+" is not found");
    }
    public ProductNotFoundException(int id) {
        super("Product with id: "+id+" is not found");
    }
}
