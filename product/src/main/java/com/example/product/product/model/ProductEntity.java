package com.example.product.product.model;

import com.example.product.user.model.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int id;

    private String name;

    private int price_cents;

    private int stock;

    private boolean active;

    @ManyToMany
    private List<UserEntity> usersBuyers=new ArrayList<>();
}
