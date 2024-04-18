package com.example.springboot_project.controller;

import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@Tag(name = "API Accounts", description = "Методы для работы с Account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @Operation(summary = "Получение всех accounts")
    public List<AccountDTO> showAllAccounts() {
        List<AccountDTO> allAccounts = accountService.getAllAccounts();
        return allAccounts;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение account по id")
    public AccountDTO getAccountById(
            @Parameter(description = "Уникальный идентификатор account")
            @PathVariable("id") int id) {
        AccountDTO accountDTO = accountService.getAccount(id);
        if (accountDTO == null) {
            throw new NoSuchElementException("There is no account with ID = " + id);
        }
        return accountDTO;
    }

    @PostMapping
    @Operation(summary = "Создание account")
    public AccountDTO create(@RequestBody AccountDTO accountDTO) {
        return accountService.saveAccount(accountDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение account по id")
    public AccountDTO update(
            @Parameter(description = "Уникальный идентификатор account")
            @PathVariable int id, @RequestBody AccountDTO accountDTO) {
        accountDTO.setId(id);
        accountService.saveAccount(accountDTO);
        return accountDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление account по id")
    public String delete(
            @Parameter(description = "Уникальный идентификатор account")
            @PathVariable("id") int id) {
        accountService.deleteAccount(id);
        return "Account with ID " + id + " was deleted";
    }
}
