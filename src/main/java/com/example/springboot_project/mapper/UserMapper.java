package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.UserDTO;
import com.example.springboot_project.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    User dtoToUser(UserDTO userDTO);
}
