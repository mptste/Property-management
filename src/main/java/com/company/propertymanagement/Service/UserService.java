package com.company.propertymanagement.Service;

import com.company.propertymanagement.Model.UserDTO;

public interface UserService {

    UserDTO register(UserDTO userDTO);

    UserDTO login(String email, String password);


}
