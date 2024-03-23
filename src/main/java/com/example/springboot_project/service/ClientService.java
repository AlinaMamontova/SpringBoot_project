package com.example.springboot_project.service;

import com.example.springboot_project.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClientDTO();

    void saveClient(ClientDTO clientDTO);

    ClientDTO getClientDTO(int id);

    void deleteClient(int id);
}
