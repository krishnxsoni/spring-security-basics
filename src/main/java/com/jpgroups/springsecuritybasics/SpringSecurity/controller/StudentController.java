package com.jpgroups.springsecuritybasics.SpringSecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController
{
    @GetMapping("/get")
    public ResponseEntity<String> getStudents()
    {
        return ResponseEntity.ok("Student retrieved successfully");
    }

    @GetMapping("/get/csrf")
    public CsrfToken getStudentsUsingCsrf(CsrfToken csrfToken)
    {
        return csrfToken;
    }

    @PostMapping("/post")
    public String saveStudents()
    {
        return "Student saved successfully";
    }

    @PutMapping("/put")
    public String updateStudents()
    {
        return "Student updated successfully";
    }
}
