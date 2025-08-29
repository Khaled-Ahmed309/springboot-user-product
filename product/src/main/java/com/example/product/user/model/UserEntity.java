package com.example.product.user.model;


import com.example.product.product.model.ProductEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    private String name ;
    @Column(name = "role_name")
    private String role;
    private String email;

    @JsonIgnore
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_products",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))

    private List<ProductEntity> productsList=new ArrayList<>();

    public UserEntity(){

    }
    public UserEntity(String email, String role, String name, int id, List<ProductEntity> productsList, String password) {
        this.name=name;
        this.email=email;
        this.role=role;
        this.id=id;
        this.productsList=productsList;
        this.password=password;

    }
}
