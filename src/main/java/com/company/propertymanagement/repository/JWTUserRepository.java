package com.company.propertymanagement.repository;

import com.company.propertymanagement.entity.JWTUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JWTUserRepository extends JpaRepository<JWTUserEntity, Long> {
    Optional<JWTUserEntity> findByEmail(String email);
    Boolean existsByEmail(String email);
}
