package com.example.product.shared;

import com.example.product.product.exception.ProductAlreadyExistingException;
import com.example.product.product.exception.ProductNotFoundException;
import com.example.product.user.exception.UserAlreadyFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ProductAlreadyExistingException.class)
    public ResponseEntity<Map<String,String>> handleProductAlreadyExists(ProductAlreadyExistingException ex){
        Map<String,String> body=new HashMap<>();
        body.put("Error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body); //status code is: 409
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleProductNotFound(ProductNotFoundException ex){
        Map<String,String> body=new HashMap<>();
        body.put("Error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body); //status code is: 409
    }

    @ExceptionHandler(UserAlreadyFoundException.class)
    public ResponseEntity<Map<String,String>> handleProductNotFound(UserAlreadyFoundException ex){
        Map<String,String> body=new HashMap<>();
        body.put("Error",ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body); //status code is: 409
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String,List<String>> body=new HashMap<>();

        List<String> errors=ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        body.put("Errors",errors);
        return ResponseEntity.badRequest().body(body);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Invalid value for parameter: " + ex.getName());
        body.put("expectedType", ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");
        body.put("value", ex.getValue() != null ? ex.getValue().toString() : "null");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body); // 400
    }

}
