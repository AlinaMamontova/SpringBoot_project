package com.example.springboot_project.controller;

import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.dto.UserDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
@Tag(name = "API Bank", description = "Методы для работы с Bank")
public class BankController {
    private BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    @Operation(summary = "Получение всех banks")
    public List<BankDTO> showAllBanks() {
        List<BankDTO> bankDTOs = bankService.getAllBanks();
        return bankDTOs;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение bank по id")
    public BankDTO getBankById(
            @Parameter(description = "Уникальный идентификатор bank")
            @PathVariable("id") int id) {
        BankDTO bankDTO = bankService.getBank(id);
        if (bankDTO == null) {
            throw new NoSuchElementException("There is no bank with ID = " + id);
        }
        return bankService.getBank(id);
    }

    @PostMapping
    @Operation(summary = "Создание bank")
    public BankDTO create(@RequestBody BankDTO bankDTO) {
        return bankService.saveBank(bankDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение bank по id")
    public ResponseEntity<BankDTO> update(@PathVariable int id, @RequestBody BankDTO bankDTO) {
        bankDTO.setId(id);
        bankService.saveBank(bankDTO);
        return new ResponseEntity<>(bankService.saveBank(bankDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление bank по id")
    public String delete(@PathVariable("id") int id) {
        bankService.deleteBank(id);
        return "Bank with ID " + id + " was deleted";
    }
}
