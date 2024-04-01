package com.example.springboot_project.service;

import com.example.springboot_project.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);
    void deleteUser(int id);
}
