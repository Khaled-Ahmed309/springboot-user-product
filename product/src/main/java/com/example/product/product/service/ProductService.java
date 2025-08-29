package com.example.product.product.service;


import com.example.product.product.dto.Product;
import com.example.product.product.exception.ProductAlreadyExistingException;
import com.example.product.product.exception.ProductNotFoundException;
import com.example.product.product.model.ProductEntity;
import com.example.product.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductEntity addProduct(Product product){
        Optional<ProductEntity>  productEntity=productRepository.findByName(product.getName());
        if (productEntity.isPresent()) {
            throw new ProductAlreadyExistingException(product.getName());
        }

        ProductEntity newProduct=new ProductEntity();


        newProduct.setName(product.getName());
        newProduct.setStock(product.getStock());
        newProduct.setActive(product.isActive());
        newProduct.setPrice_cents(product.getPrice_cents());

        return productRepository.save(newProduct);

    }

    public Product getProduct(int id){
        Optional<ProductEntity> myProduct=productRepository.findById(id);
        if (myProduct.isEmpty()){
            throw new ProductNotFoundException(id);
        }
        return new Product(
                myProduct.get().getName(),
                myProduct.get().getPrice_cents(),
                myProduct.get().isActive(),
                myProduct.get().getStock());
    }

    public void updateProduct(int id, Product product) {
        Optional<ProductEntity> theProduct = productRepository.findById(id);
        if (theProduct.isEmpty()) {
            throw new ProductNotFoundException(id);
        }

        theProduct.get().setName(product.getName());
        theProduct.get().setStock(product.getStock());
        theProduct.get().setPrice_cents(product.getPrice_cents());
        theProduct.get().setActive(product.isActive());
       productRepository.save(theProduct.get());
    }
}
