package com.jpgroups.springsecuritybasics.SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false, unique = true)
    private String password;

    @Column(name = "IS_ACTIVE")
    private Boolean active;

    @ManyToMany
    @JoinTable(name = "USER_ROLE_MAPPING",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> role = new HashSet<>();

}
