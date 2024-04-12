package com.company.propertymanagement.service.implementation;


import com.company.propertymanagement.entity.JWTUserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(JWTUserEntity jwtUser) {
        List<GrantedAuthority> authorities = jwtUser.getRoles().stream()
                .map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRoleName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                jwtUser.getId(), jwtUser.getFirstName(), jwtUser.getLastName(),
                jwtUser.getEmail(), jwtUser.getPhone(), jwtUser.getPassword(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
