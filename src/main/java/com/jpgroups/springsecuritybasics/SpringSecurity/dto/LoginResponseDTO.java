package com.jpgroups.springsecuritybasics.SpringSecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO
{
    private String accessToken;
}
