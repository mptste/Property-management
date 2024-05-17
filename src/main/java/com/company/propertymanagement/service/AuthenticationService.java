package com.company.propertymanagement.service;

import com.company.propertymanagement.model.LoginDTO;
import com.company.propertymanagement.model.SignupDTO;

public interface AuthenticationService {

    public void login(LoginDTO loginDTO);
    public void signup(SignupDTO signupDTO);
}
