package com.company.propertymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String ownerName;
    @NotBlank(message = "Enter email")
    @NotEmpty(message = "Email field cannot be empty")
    @Size(min = 1, max = 50, message = "Email should be between 1-50 characters long")
    private String ownerEmail;
    private String phone;
    @NotBlank(message = "Password can't be blank")
    @NotEmpty(message = "Password field cannot be empty")
    private String password;

    private String houseNum;
    private String street;
    private String city;
    private String zipCode;
    private String country;


}
