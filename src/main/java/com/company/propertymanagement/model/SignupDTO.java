package com.company.propertymanagement.model;

import lombok.Data;

@Data
public class SignupDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
