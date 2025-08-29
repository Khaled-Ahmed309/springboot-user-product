package com.example.product.user.exception;

public class UserAlreadyFoundException extends RuntimeException {
    public UserAlreadyFoundException(String email) {
        super("User with email: "+email+" is already found, try with another email");
    }
}
