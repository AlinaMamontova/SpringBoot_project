package com.example.springboot_project.service;

import com.example.springboot_project.dao.UserRepository;
import com.example.springboot_project.dto.UserDTO;
import com.example.springboot_project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void saveUser(UserDTO userDTO) {
        userRepository.save(userMapper.dtoToUser(userDTO));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
