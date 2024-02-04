package com.company.propertymanagement.service.implementation;

import com.company.propertymanagement.converter.UserConverter;
import com.company.propertymanagement.entity.AddressEntity;
import com.company.propertymanagement.entity.UserEntity;
import com.company.propertymanagement.exception.BusinessException;
import com.company.propertymanagement.exception.ErrorModel;
import com.company.propertymanagement.model.UserDTO;
import com.company.propertymanagement.repository.AddressRepository;
import com.company.propertymanagement.repository.UserRepository;
import com.company.propertymanagement.service.UserService;
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
    private AddressRepository addressRepository;

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

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNum(userDTO.getHouseNum());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setZipCode(userDTO.getZipCode());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCountry(userDTO.getCountry());

        addressRepository.save(addressEntity);

        userDTO = userConverter.convertEntityToDTO(userEntity);


        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO;
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
