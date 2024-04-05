package com.example.springboot_project.controller;

import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> showAllClients() {
        List<ClientDTO> allClients = clientService.getAllClientDTO();
        return allClients;
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable("id") int id) {
        ClientDTO clientDTO = clientService.getClientDTO(id);
        if (clientDTO == null) {
            throw new NoSuchElementException("There is no client with ID = " + id);
        }
        return clientDTO;
    }

    @PostMapping
    public void create(@RequestBody ClientDTO clientDTO) {
        clientService.saveClient(clientDTO);
    }

    @PutMapping("/{id}")
    public ClientDTO update(@PathVariable int id, @RequestBody ClientDTO clientDTO) {
        clientDTO.setId(id);
        clientService.saveClient(clientDTO);
        return clientDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        clientService.deleteClient(id);
        return "Client with ID " + id + " was deleted";
    }
}
