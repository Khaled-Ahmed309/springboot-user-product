package com.example.product.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUser {

    @Email(message = "Enter valid Email")
    @NotBlank(message = "Email must not be blank")
    private String email;


    @NotNull(message = "Password must not be null")
    @Size(min = 5,message = "Password valid for at least 5 characters long")
    private String password;

}
