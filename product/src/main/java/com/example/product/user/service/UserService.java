package com.example.product.user.service;

import com.example.product.product.exception.ProductAlreadyExistingException;
import com.example.product.product.exception.ProductNotFoundException;
import com.example.product.product.model.ProductEntity;
import com.example.product.product.repository.ProductRepository;
import com.example.product.user.dto.User;
import com.example.product.user.exception.UserAlreadyFoundException;
import com.example.product.user.model.UserEntity;
import com.example.product.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ProductRepository productRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addNewUser(User user) {
        Optional<UserEntity> currentUser = userRepository.findByEmail(user.getEmail());
        if (currentUser.isPresent()) {
            throw new UserAlreadyFoundException(user.getEmail());
        }

        UserEntity newUser = new UserEntity();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());

        UserEntity savedUser = userRepository.save(newUser);

        return new User(savedUser.getName(), savedUser.getEmail(), savedUser.getRole());
    }

    @Transactional
    public void addProductUser(String productName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();

        ProductEntity currentProduct = productRepository.findByName(productName)
                .orElseThrow(() -> new ProductNotFoundException(productName));
        UserEntity currentUser = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not found with email: " + userEmail));

        if (!currentUser.getProductsList().contains(currentProduct)) {
            currentUser.getProductsList().add(currentProduct);
            userRepository.save(currentUser);
        }else {
            throw new ProductAlreadyExistingException(productName);
        }
    }

}
