package com.company.propertymanagement.service.implementation;

import com.company.propertymanagement.entity.JWTUserEntity;
import com.company.propertymanagement.entity.UserEntity;
import com.company.propertymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByOwnerEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with the following email address " + email + " does not exist."));
        UserDetails userDetails = UserDetailsImpl.build(new JWTUserEntity());
        return userDetails;
    }
}
