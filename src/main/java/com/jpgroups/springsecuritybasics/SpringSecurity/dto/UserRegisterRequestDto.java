package com.jpgroups.springsecuritybasics.SpringSecurity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto
{
    private String username;

    private String password;

    @Override
    public String toString() {
        return "UserRegisterRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
