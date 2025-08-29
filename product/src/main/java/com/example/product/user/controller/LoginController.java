package com.example.product.user.controller;

import com.example.product.user.dto.LoginUser;
import com.example.product.user.model.UserEntity;
import com.example.product.user.service.CustomerDetailsService;
import com.example.product.user.service.JwtUtilService;
import com.example.product.user.service.UserPrinciple;
import com.example.product.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CachingUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilService jwtUtilService;


    public LoginController(UserService userService, AuthenticationManager authenticationManager, JwtUtilService jwtUtilService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtilService = jwtUtilService;
    }

    @PostMapping("/login")

    public ResponseEntity<?> login(@RequestBody @Valid LoginUser user){
        Authentication auth=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        UserPrinciple currentUser= (UserPrinciple) auth.getPrincipal();
        String token=jwtUtilService.generateToken(currentUser.getUser());
        Map<String ,String > body=new HashMap<>();
        body.put("toke",token);

        return ResponseEntity.ok(body);

    }
}
/*
*  @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest loginRequest){
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        UserEntity user= (UserEntity) auth.getPrincipal();
        String token =jwtUtil.generateToken(user);
        LoginResponse loginResponse=new LoginResponse(token);
        return ResponseEntity.ok(loginResponse);
    }*/