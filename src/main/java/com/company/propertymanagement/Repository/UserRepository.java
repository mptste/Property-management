package com.company.propertymanagement.Repository;

import com.company.propertymanagement.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
