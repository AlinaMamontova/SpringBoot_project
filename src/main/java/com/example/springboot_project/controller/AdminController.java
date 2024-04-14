package com.example.springboot_project.controller;

import com.example.springboot_project.config.ApplicationConfig;
import com.example.springboot_project.dto.UserDTO;
import com.example.springboot_project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/users")
@Tag(name = "API Admin", description = "Методы для работы с Account")
public class AdminController {
    private UserService userService;
    private ApplicationConfig applicationConfig;

    @Autowired
    public AdminController(UserService userService, ApplicationConfig applicationConfig) {
        this.userService = userService;
        this.applicationConfig = applicationConfig;
    }

    //    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    @Operation(summary = "Создание user")
//    public void create(@RequestBody UserDTO userDTO) {
//        String encodePassword = applicationConfig.passwordEncoder().encode(userDTO.getPassword());
//        userDTO.setPassword(encodePassword);
//        userService.saveUser(userDTO);
//    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Создание user")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.saveUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Изменение user")
    public UserDTO update(@RequestBody UserDTO userDTO) {
        String encodePassword = applicationConfig.passwordEncoder().encode(userDTO.getPassword());
        userDTO.setPassword(encodePassword);
        userService.saveUser(userDTO);
        return userDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление user по id")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "User with ID " + id + " was deleted";
    }
}
