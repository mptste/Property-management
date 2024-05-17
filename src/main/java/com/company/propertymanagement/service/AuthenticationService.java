package com.company.propertymanagement.service;

import com.company.propertymanagement.model.JWTResponseDTO;
import com.company.propertymanagement.model.LoginDTO;
import com.company.propertymanagement.model.SignupDTO;

public interface AuthenticationService {

    public JWTResponseDTO login(LoginDTO loginDTO);
    public Long signup(SignupDTO signupDTO);
}
