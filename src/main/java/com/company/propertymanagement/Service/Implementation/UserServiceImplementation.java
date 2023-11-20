package com.company.propertymanagement.Service.Implementation;

import com.company.propertymanagement.Converter.UserConverter;
import com.company.propertymanagement.Entity.UserEntity;
import com.company.propertymanagement.Model.UserDTO;
import com.company.propertymanagement.Repository.UserRepository;
import com.company.propertymanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        return null;
    }
}
