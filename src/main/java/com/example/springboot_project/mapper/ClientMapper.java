package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.entity.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDTO(Client client);
    Client dtoToClient(ClientDTO clientDTO);
}
