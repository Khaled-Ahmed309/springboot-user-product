package com.example.product.user.controller;

import com.example.product.user.dto.User;
import com.example.product.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userService.addNewUser(user));

    }

    @PostMapping("/addProduct/{name}")
    public ResponseEntity<?> addProduct(@PathVariable(name = "name") String name) {
        try {
            userService.addProductUser(name);
            return ResponseEntity.ok("Successfully add product ");
        } catch (Exception e) {
            throw new RuntimeException("Error while adding the product: "+e.getMessage());
        }
    }

}
