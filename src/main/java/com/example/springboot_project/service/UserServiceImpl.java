package com.example.springboot_project.service;

import com.example.springboot_project.dao.UserRepository;
import com.example.springboot_project.dto.UserDTO;
import com.example.springboot_project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void saveUser(UserDTO userDTO) {
        userRepository.save(userMapper.dtoToUser(userDTO));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
