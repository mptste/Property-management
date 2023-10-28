package com.company.propertymanagement.Model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Property;

@Getter
@Setter
public class PropertyDTO {

    private Long id;
    private String title;
    private String description;
    private String ownerName;
    private String ownerEmail;
    private Double price;
    private String address;
}
