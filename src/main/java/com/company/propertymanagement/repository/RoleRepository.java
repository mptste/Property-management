package com.company.propertymanagement.repository;

import com.company.propertymanagement.entity.RoleEntity;
import com.company.propertymanagement.enums.URole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(URole uRole);

}
