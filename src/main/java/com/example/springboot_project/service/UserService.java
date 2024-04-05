package com.example.springboot_project.service;

import com.example.springboot_project.dto.UserDTO;
import com.example.springboot_project.entity.User;

import java.util.Optional;

public interface UserService {
    void saveUser(UserDTO userDTO);

    void deleteUser(int id);

}
