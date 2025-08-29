package com.example.product.user.service;

import com.example.product.user.model.UserEntity;
import com.example.product.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomerDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user=userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

        return new UserPrinciple
                (new UserEntity
                        (user.getEmail(),
                                user.getRole(),
                                user.getName(),
                                user.getId(),
                                user.getProductsList(),
                                user.getPassword()));
    }
}
