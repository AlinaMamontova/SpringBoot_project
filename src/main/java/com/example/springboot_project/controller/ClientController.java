package com.example.springboot_project.controller;

import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clients")
    public List<ClientDTO> showAllClients() {
        List<ClientDTO> allClients = clientService.getAllClientDTO();
        return allClients;
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClientById(@PathVariable("id") int id) {
        ClientDTO clientDTO = clientService.getClientDTO(id);
        return clientDTO;
    }

    @PostMapping("/clients")
    public void addNewAccount(@RequestBody ClientDTO clientDTO) {
        clientService.saveClient(clientDTO);
    }

    @PutMapping("/clients")
    public ClientDTO updateClient(@RequestBody ClientDTO clientDTO) {
        clientService.saveClient(clientDTO);
        return clientDTO;
    }

    @DeleteMapping("/clients/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteClient(@PathVariable("id") int id) {
        clientService.deleteClient(id);
        return "Client with ID " + id + " was deleted";
    }
}
