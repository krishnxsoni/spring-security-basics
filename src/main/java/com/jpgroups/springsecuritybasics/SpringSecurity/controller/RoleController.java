package com.jpgroups.springsecuritybasics.SpringSecurity.controller;

import com.jpgroups.springsecuritybasics.SpringSecurity.entity.Role;
import com.jpgroups.springsecuritybasics.SpringSecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController
{
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseEntity<String> addRole(@RequestBody Role role)
    {
        String response = roleService.addRole(role);
        return ResponseEntity.ok(response);
    }
}
