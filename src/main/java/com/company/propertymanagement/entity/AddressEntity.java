package com.company.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ADDRESS_TABLE")
@Getter
@Setter
@NoArgsConstructor

public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String houseNum;
    private String street;
    private String city;
    private String zipCode;
    private String country;

    @OneToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;
}
