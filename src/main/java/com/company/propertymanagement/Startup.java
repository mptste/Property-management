package com.company.propertymanagement;

import com.company.propertymanagement.entity.RoleEntity;
import com.company.propertymanagement.enums.URole;
import com.company.propertymanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Startup implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<RoleEntity> optRoleAd = roleRepository.findByRoleName(URole.ROLE_ADMIN);
        if (optRoleAd.isEmpty()) {
            RoleEntity re = new RoleEntity();
            re.setRoleName(URole.ROLE_ADMIN);
            roleRepository.save(re);
        }
        Optional<RoleEntity> optRoleMod = roleRepository.findByRoleName(URole.ROLE_MODERATOR);
        if (optRoleMod.isEmpty()) {
            RoleEntity re = new RoleEntity();
            re.setRoleName(URole.ROLE_MODERATOR);
            roleRepository.save(re);
        }
        Optional<RoleEntity> optRoleUser = roleRepository.findByRoleName(URole.ROLE_USER);
        if (optRoleUser.isEmpty()) {
            RoleEntity re = new RoleEntity();
            re.setRoleName(URole.ROLE_USER);
            roleRepository.save(re);
        }
    }
}
