package com.example.product.product.controller;


import com.example.product.product.dto.Product;
import com.example.product.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product/admin")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody @Valid Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PreAuthorize("hasRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") int id){
     return ResponseEntity.ok(productService.getProduct(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(name="id") int id,
                                           @RequestBody @Valid Product product) {
        productService.updateProduct(id,product);
        Map<String,String > body=new HashMap<>();
        body.put("Message","Successfully updated the product");
        return ResponseEntity.ok(body);

    }
    // user will add this product to his account:


}
