package com.company.propertymanagement.service;

import com.company.propertymanagement.model.UserDTO;

public interface UserService {

    UserDTO register(UserDTO userDTO);

    UserDTO login(String email, String password);


}
