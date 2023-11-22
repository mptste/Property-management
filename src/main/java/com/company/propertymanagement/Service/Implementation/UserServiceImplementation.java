package com.company.propertymanagement.Service.Implementation;

import com.company.propertymanagement.Converter.UserConverter;
import com.company.propertymanagement.Entity.UserEntity;
import com.company.propertymanagement.Exception.BusinessException;
import com.company.propertymanagement.Exception.ErrorModel;
import com.company.propertymanagement.Model.UserDTO;
import com.company.propertymanagement.Repository.UserRepository;
import com.company.propertymanagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> optUe = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (optUe.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_IN_USE");
            errorModel.setMessage("This email already belongs to an existing user");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        userDTO = userConverter.convertEntityToDTO(userEntity);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if (optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(optionalUserEntity.get());
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect email or password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }
}
