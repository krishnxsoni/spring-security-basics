package com.jpgroups.springsecuritybasics.SpringSecurity.service;

import com.jpgroups.springsecuritybasics.SpringSecurity.entity.Role;
import com.jpgroups.springsecuritybasics.SpringSecurity.repository.RoleRepository;
import com.jpgroups.springsecuritybasics.SpringSecurity.utility.Constants;
import org.springframework.stereotype.Service;

@Service
public class RoleService
{
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository)
    {
        this.roleRepository = roleRepository;
    }

    public String addRole(Role role){
        if(role!=null){
            if(roleRepository.findByRoleCode(role.getRoleCode()).isEmpty())
            {
                role.setDeleteFlag(Constants.DELETE_FLAG_F); // every new role created should be ACTIVE!
                roleRepository.save(role);
                return Constants.SUCCESS;
            }
            else {
                return Constants.DUPLICATE;
            }
        }
        return Constants.FAILURE;
    }

}
