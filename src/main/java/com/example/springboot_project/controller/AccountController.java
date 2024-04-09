package com.example.springboot_project.controller;

import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDTO> showAllAccounts() {
        List<AccountDTO> allAccounts = accountService.getAllAccounts();
        return allAccounts;
    }

    @GetMapping("/{id}")
    public AccountDTO getAccountById(@PathVariable("id") int id) {
        AccountDTO accountDTO = accountService.getAccount(id);
        if (accountDTO == null) {
            throw new NoSuchElementException("There is no account with ID = " + id);
        }
        return accountDTO;
    }

    @PostMapping
    public AccountDTO create(@RequestBody AccountDTO accountDTO) {
        return accountService.saveAccount(accountDTO);
    }

    @PutMapping("/{id}")
    public AccountDTO update(@PathVariable int id, @RequestBody AccountDTO accountDTO) {
        accountDTO.setId(id);
        accountService.saveAccount(accountDTO);
        return accountDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        accountService.deleteAccount(id);
        return "Account with ID " + id + " was deleted";
    }
}
