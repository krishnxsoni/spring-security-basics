package com.jpgroups.springsecuritybasics.SpringSecurity.repository;

import com.jpgroups.springsecuritybasics.SpringSecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long>
{
    Optional<Role> findByRoleName(String roleName);

    Optional<Role> findByRoleCode(String roleCode);
}
