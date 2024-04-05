package com.example.springboot_project.controller;

import com.example.springboot_project.config.ApplicationConfig;
import com.example.springboot_project.dao.UserRepository;
import com.example.springboot_project.dto.UserDTO;
import com.example.springboot_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
public class AdminController {
    private UserService userService;
    private ApplicationConfig applicationConfig;

    @Autowired
    public AdminController(UserService userService, ApplicationConfig applicationConfig) {
        this.userService = userService;
        this.applicationConfig = applicationConfig;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void create(@RequestBody UserDTO userDTO) {
        String encodePassword = applicationConfig.passwordEncoder().encode(userDTO.getPassword());
        userDTO.setPassword(encodePassword);
        userService.saveUser(userDTO);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO update(@RequestBody UserDTO userDTO) {
        String encodePassword = applicationConfig.passwordEncoder().encode(userDTO.getPassword());
        userDTO.setPassword(encodePassword);
        userService.saveUser(userDTO);
        return userDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "User with ID " + id + " was deleted";
    }
}
