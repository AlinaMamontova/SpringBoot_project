package com.example.springboot_project.controller;

import com.example.springboot_project.auth.AuthenticationRequest;
import com.example.springboot_project.auth.AuthenticationResponce;
import com.example.springboot_project.auth.AuthenticationService;
import com.example.springboot_project.auth.RegisterRequest;
import com.example.springboot_project.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final UserRepository userRepository;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Пользователь с таким email уже существует");
        }
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}
