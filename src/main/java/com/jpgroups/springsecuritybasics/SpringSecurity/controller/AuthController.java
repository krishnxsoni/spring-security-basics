package com.jpgroups.springsecuritybasics.SpringSecurity.controller;


import com.jpgroups.springsecuritybasics.SpringSecurity.dto.LoginRequestDTO;
import com.jpgroups.springsecuritybasics.SpringSecurity.dto.LoginResponseDTO;
import com.jpgroups.springsecuritybasics.SpringSecurity.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO requestDTO)
    {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(requestDTO.getUsername(),
                requestDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        String authToken = jwtService.generateToken(authentication);
        log.info("Auth Token: {}", authToken);
        return new LoginResponseDTO(authToken);
    }
}
