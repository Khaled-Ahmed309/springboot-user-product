package com.example.product.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class Product {


    @NotBlank(message = "Name must not be blank")
    private String name;

    @Positive(message = "Price must be greater than zero")
    @NotNull(message = "Price cent must not be blank")
    private int price_cents;

    @Positive(message = "Stock must be greater than zero")
    @NotNull(message = "Stock must not be null")
    private int stock;

    @NotNull(message = "Flag must be not null")
    private boolean active;

    public Product(String name, int price_cents, boolean active, int stock) {
        this.name = name;
        this.price_cents = price_cents;
        this.active = active;
        this.stock = stock;
    }
}
