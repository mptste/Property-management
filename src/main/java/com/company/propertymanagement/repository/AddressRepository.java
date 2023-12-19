package com.company.propertymanagement.repository;

import com.company.propertymanagement.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
}
