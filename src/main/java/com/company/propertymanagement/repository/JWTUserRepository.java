package com.company.propertymanagement.repository;

import com.company.propertymanagement.entity.JWTUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JWTUserRepository extends JpaRepository<JWTUserEntity, Long> {
    Optional<JWTUserEntity> findByEmail(String email);
    Boolean existByEmail(String email);
}
