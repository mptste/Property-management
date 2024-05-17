package com.company.propertymanagement.service.implementation;

import com.company.propertymanagement.model.LoginDTO;
import com.company.propertymanagement.model.SignupDTO;
import com.company.propertymanagement.service.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthenticServiceImpl implements AuthenticationService {
    @Override
    public void login(@RequestBody LoginDTO loginDTO) {

    }

    @Override
    public void signup(SignupDTO signupDTO) {

    }
}
