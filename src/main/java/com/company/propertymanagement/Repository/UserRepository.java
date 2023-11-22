package com.company.propertymanagement.Repository;

import com.company.propertymanagement.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByOwnerEmailAndPassword(String email, String password);

    Optional<UserEntity> findByOwnerEmail(String email);
}
