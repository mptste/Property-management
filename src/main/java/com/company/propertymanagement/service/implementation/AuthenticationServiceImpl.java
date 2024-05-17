package com.company.propertymanagement.service.implementation;

import com.company.propertymanagement.entity.JWTUserEntity;
import com.company.propertymanagement.entity.RoleEntity;
import com.company.propertymanagement.enums.URole;
import com.company.propertymanagement.exception.JWTBusinessException;
import com.company.propertymanagement.model.JWTErrorDTO;
import com.company.propertymanagement.model.JWTResponseDTO;
import com.company.propertymanagement.model.LoginDTO;
import com.company.propertymanagement.model.SignupDTO;
import com.company.propertymanagement.repository.JWTUserRepository;
import com.company.propertymanagement.repository.RoleRepository;
import com.company.propertymanagement.service.AuthenticationService;
import com.company.propertymanagement.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private JWTUserRepository jwtUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public JWTResponseDTO login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtUtil.generateJWTToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        JWTResponseDTO jwtResponseDTO = new JWTResponseDTO();
        jwtResponseDTO.setFirstName(userDetails.getFirstName());
        jwtResponseDTO.setLastName(userDetails.getLastName());
        jwtResponseDTO.setToken(jwtToken);
        jwtResponseDTO.setRoles(roles);
        jwtResponseDTO.setId(userDetails.getId());

        return jwtResponseDTO;
    }

    @Override
    public Long signup(SignupDTO signupDTO) {
        List<JWTErrorDTO> errorDTOS = new ArrayList<>();
        if (jwtUserRepository.existsByEmail(signupDTO.getEmail())) {
            errorDTOS.add(new JWTErrorDTO("AUTH_001", "Email address already exists"));
            throw new JWTBusinessException(errorDTOS);
        }

        String hashedPass = passwordEncoder.encode(signupDTO.getPassword());
        Set<RoleEntity> roleEntities = new HashSet<>();

        Optional<RoleEntity> optionalRole = null;

        if (signupDTO.getRole() != null && signupDTO.getRole().equals("ADMIN")) {
            optionalRole = roleRepository.findByRoleName(URole.ROLE_ADMIN);
        } else {
            optionalRole = roleRepository.findByRoleName(URole.ROLE_USER);
        }

        roleEntities.add(optionalRole.get());

        JWTUserEntity jwtUserEntity = new JWTUserEntity();
        jwtUserEntity.setEmail(signupDTO.getEmail());
        jwtUserEntity.setFirstName(signupDTO.getFirstName());
        jwtUserEntity.setLastName(signupDTO.getLastName());
        jwtUserEntity.setPhone(signupDTO.getPhone());
        jwtUserEntity.setPassword(hashedPass);
        jwtUserEntity.setRoles(roleEntities);

        jwtUserEntity = jwtUserRepository.save(jwtUserEntity);
        return jwtUserEntity.getId();
    }
}
