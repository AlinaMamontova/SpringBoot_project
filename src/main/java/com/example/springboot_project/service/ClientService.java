package com.example.springboot_project.service;

import com.example.springboot_project.dao.ClientRepository;
import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.entity.Client;
import com.example.springboot_project.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;


    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDTO> getAllClientDTO() {
        List<Client> allClients = clientRepository.findAll();
        List<ClientDTO> clientsDTOs = allClients.stream().map(clientMapper::toDTO).toList();
        return clientsDTOs;
    }

    public void saveClient(ClientDTO clientDTO) {
        clientRepository.save(clientMapper.dtoToClient(clientDTO));
    }

    public ClientDTO getClientDTO(int id) {
        Client client = null;
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            client = optionalClient.get();
        }
        return clientMapper.toDTO(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}

