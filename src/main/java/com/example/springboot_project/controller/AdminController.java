package com.example.springboot_project.controller;

import com.example.springboot_project.auth.AuthenticationService;
import com.example.springboot_project.auth.RegisterRequest;
import com.example.springboot_project.config.ApplicationConfig;
import com.example.springboot_project.dto.UserDTO;
import com.example.springboot_project.entity.User;
import com.example.springboot_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private ApplicationConfig applicationConfig;

    @Autowired
    public AdminController(UserService userService, ApplicationConfig applicationConfig) {
        this.userService = userService;
        this.applicationConfig = applicationConfig;
    }

    @PostMapping("/users")
    @Secured("ADMIN")
    public void addUser(@RequestBody UserDTO userDTO) {
        String encodePassword = applicationConfig.passwordEncoder().encode(userDTO.getPassword());
        userDTO.setPassword(encodePassword);
        userService.saveUser(userDTO);
    }

    @PutMapping("/users")
    @Secured("ADMIN")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        String encodePassword = applicationConfig.passwordEncoder().encode(userDTO.getPassword());
        userDTO.setPassword(encodePassword);
        userService.saveUser(userDTO);
        return userDTO;
    }

    @DeleteMapping("/users/{id}")
    @Secured("ADMIN")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "User with ID " + id + " was deleted";
    }
}
