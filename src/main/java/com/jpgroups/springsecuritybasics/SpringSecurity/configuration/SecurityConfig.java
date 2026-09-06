package com.jpgroups.springsecuritybasics.SpringSecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
*
*This Configuration class is created by Krishna,
* to secure the Passwords using the BCryptPasswordEncoder Hashing Algorithm!!!
*
**/

@Configuration
public class SecurityConfig
{
    /*@Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }*/
}
