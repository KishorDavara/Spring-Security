package com.express.controller;

import com.express.model.Customer;
import com.express.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    ResponseEntity<String> createUser(@RequestBody Customer customer) {
        ResponseEntity response = null;
        try {
            String hasedPassword = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hasedPassword);
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));
            Customer result = customerRepository.save(customer);
            if (result != null) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("User registered successfully.");
            }
        } catch (Exception ex) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to " + ex.getMessage());
        }
        return response;
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        return !customers.isEmpty() ? customers.get(0) : null;
    }
}