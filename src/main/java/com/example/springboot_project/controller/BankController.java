package com.example.springboot_project.controller;

import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BankController {
    private BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/banks")
    public List<BankDTO> showAllBanks() {
        List<BankDTO> bankDTOs = bankService.getAllBanks();
        return bankDTOs;
    }

    @GetMapping("/banks/{id}")
    public BankDTO getBankById(@PathVariable("id") int id) {
        BankDTO bankDTO = bankService.getBank(id);
        return bankService.getBank(id);
    }

    @PostMapping("/banks")
    public void addNewBank(@RequestBody BankDTO bankDTO) {
        bankService.saveBank(bankDTO);
    }

    @PutMapping("/banks")
    public BankDTO updateBank(@RequestBody BankDTO bankDTO) {
        bankService.saveBank(bankDTO);
        return bankDTO;
    }

    @DeleteMapping("/banks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBank(@PathVariable("id") int id) {
        bankService.deleteBank(id);
        return "Bank with ID " + id + " was deleted";
    }
}
