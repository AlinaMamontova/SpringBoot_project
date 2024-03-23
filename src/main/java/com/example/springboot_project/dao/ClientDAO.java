package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Client;

import java.util.List;

public interface ClientDAO {
    List<Client> getAllClients();

    void saveClient(Client client);

    Client getClient(int id);

    void deleteClient(int id);
}
