package com.company.propertymanagement.implementation;

import com.company.propertymanagement.Converter.PropertyConverter;
import com.company.propertymanagement.Entity.PropertyEntity;
import com.company.propertymanagement.Model.PropertyDTO;
import com.company.propertymanagement.Repository.PropertyRepository;
import com.company.propertymanagement.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImplementation implements PropertyService {

    @Value("${pms.dummy:}")
    private String dummy;

    @Value("${spring.datasource.url:}")
    private String dbUrl;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);

        PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
        return dto;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {

        System.out.println("Inside service: " + dummy);
        List<PropertyEntity> listOfProperties = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();

        for (PropertyEntity pe : listOfProperties) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optionalEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optionalEn.isPresent()) {

            PropertyEntity pe = optionalEn.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setAddress(propertyDTO.getAddress());
            pe.setOwnerEmail(propertyDTO.getOwnerEmail());
            pe.setOwnerName(propertyDTO.getOwnerName());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);

            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDesc(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optionalEn.isPresent()) {

            PropertyEntity pe = optionalEn.get();
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {

        Optional<PropertyEntity> optionalEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (optionalEn.isPresent()){

            PropertyEntity pe = optionalEn.get();
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityToDTO(pe);
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

}


