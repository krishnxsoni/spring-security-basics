package com.jpgroups.springsecuritybasics.SpringSecurity.controller;

import com.jpgroups.springsecuritybasics.SpringSecurity.dto.UserRegisterRequestDto;
import com.jpgroups.springsecuritybasics.SpringSecurity.dto.UserRegisterResponseDto;
import com.jpgroups.springsecuritybasics.SpringSecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello there!";
    }

    @GetMapping("/token")
    public CsrfToken getToken(CsrfToken csrfToken)
    {
        return csrfToken;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> registerUser(
            @RequestBody UserRegisterRequestDto userRegisterRequestDto)
    {
        log.info(":: Inside UserController :: registerUser() :: ");
        UserRegisterResponseDto userRegisterResponseDto = userService.registerUser(userRegisterRequestDto);
        return ResponseEntity.ok(userRegisterResponseDto);
    }


}
