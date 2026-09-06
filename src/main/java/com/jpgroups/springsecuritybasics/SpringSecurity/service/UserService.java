package com.jpgroups.springsecuritybasics.SpringSecurity.service;

import com.jpgroups.springsecuritybasics.SpringSecurity.dto.UserRegisterRequestDto;
import com.jpgroups.springsecuritybasics.SpringSecurity.dto.UserRegisterResponseDto;
import com.jpgroups.springsecuritybasics.SpringSecurity.entity.User;
import com.jpgroups.springsecuritybasics.SpringSecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto requestDto)
    {
        log.info(":: Inside UserService :: registerUser() :: ");

        UserRegisterResponseDto userRegisterResponseDto = new UserRegisterResponseDto();

        if (requestDto!=null)
        {
            log.info(":: requestDto ==> :: "+requestDto.toString());
            log.info(":: Registering new User with username ==> :: "+requestDto.getUsername());
            User user = new User();
            user.setUsername(requestDto.getUsername());
            user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            user.setActive(true);
            userRepository.save(user);

            userRegisterResponseDto.setUsername(requestDto.getUsername());
            userRegisterResponseDto.setMessage("User Registered Successfully");
            return userRegisterResponseDto;
        }
        userRegisterResponseDto.setUsername(null);
        userRegisterResponseDto.setMessage("Something went wrong");
        return userRegisterResponseDto;
    }
}
