package com.company.propertymanagement.Repository;

import com.company.propertymanagement.Entity.PropertyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {


}
