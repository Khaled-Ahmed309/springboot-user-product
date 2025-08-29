package com.example.product.config;


import com.example.product.user.model.UserEntity;
import com.example.product.user.repository.UserRepository;
import com.example.product.user.service.CustomerDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductUsernameAndpassword implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerDetailsService customerDetailsService;

    public ProductUsernameAndpassword(PasswordEncoder passwordEncoder, UserRepository userRepository, CustomerDetailsService customerDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.customerDetailsService = customerDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email=authentication.getName();
        String pwd=authentication.getCredentials().toString();
        var customerDetails=customerDetailsService.loadUserByUsername(email);

        if (passwordEncoder.matches(pwd,customerDetails.getPassword()))
        {

            return new UsernamePasswordAuthenticationToken(customerDetails, null, customerDetails.getAuthorities());
        }
        throw new BadCredentialsException("Invalid credentials, password or username is incorrect");


    }
    public List<GrantedAuthority> getAuthorities(SimpleGrantedAuthority roles){
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
