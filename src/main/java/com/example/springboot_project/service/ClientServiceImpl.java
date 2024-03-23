package com.example.springboot_project.service;

import com.example.springboot_project.dao.ClientDAO;
import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.entity.Client;
import com.example.springboot_project.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientDAO clientDAO;
    private ClientMapper clientMapper;


    @Autowired
    public ClientServiceImpl(ClientDAO clientDAO, ClientMapper clientMapper) {
        this.clientDAO = clientDAO;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional
    public List<ClientDTO> getAllClientDTO() {
        List<Client> allClients = clientDAO.getAllClients();
        List<ClientDTO> clientsDTOs = allClients.stream().map(clientMapper::toDTO).toList();
        return clientsDTOs;
    }

    @Override
    @Transactional
    public void saveClient(ClientDTO clientDTO) {
        clientDAO.saveClient(clientMapper.dtoToClient(clientDTO));
    }

    @Override
    @Transactional
    public ClientDTO getClientDTO(int id) {
        return clientMapper.toDTO(clientDAO.getClient(id));
    }

    @Override
    @Transactional
    public void deleteClient(int id) {
        clientDAO.deleteClient(id);
    }
}

