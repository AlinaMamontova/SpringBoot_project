package com.example.springboot_project.controller;

import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {
    private BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<BankDTO> showAllBanks() {
        List<BankDTO> bankDTOs = bankService.getAllBanks();
        return bankDTOs;
    }

    @GetMapping("/{id}")
    public BankDTO getBankById(@PathVariable("id") int id) {
        BankDTO bankDTO = bankService.getBank(id);
        if (bankDTO == null) {
            throw new NoSuchElementException("There is no bank with ID = " + id);
        }
        return bankService.getBank(id);
    }

    @PostMapping
    public void create(@RequestBody BankDTO bankDTO) {
        bankService.saveBank(bankDTO);
    }

    @PutMapping("/{id}")
    public BankDTO update(@PathVariable int id, @RequestBody BankDTO bankDTO) {
        bankDTO.setId(id);
        bankService.saveBank(bankDTO);
        return bankDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        bankService.deleteBank(id);
        return "Bank with ID " + id + " was deleted";
    }
}
