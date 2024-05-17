package com.company.propertymanagement.controller;

import com.company.propertymanagement.model.SignupDTO;
import com.company.propertymanagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openapi/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    public void login() {

    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupDTO signupDTO) {
        authenticationService.signup(signupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
