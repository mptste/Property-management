package com.company.propertymanagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class JWTUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_to_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<RoleEntity> roles = new HashSet<>();


}
