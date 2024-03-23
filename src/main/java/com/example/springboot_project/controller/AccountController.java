package com.example.springboot_project.controller;

import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> showAllAccounts() {
        List<AccountDTO> allAccounts = accountService.getAllAccounts();
        return allAccounts;
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getBankById(@PathVariable("id") int id) {
        AccountDTO accountDTO = accountService.getAccount(id);
        return accountDTO;
    }

    @PostMapping("/accounts")
    public void addNewAccount(@RequestBody AccountDTO accountDTO) {
        accountService.saveAccount(accountDTO);
    }

    @PutMapping("/accounts")
    public AccountDTO updateBank(@RequestBody AccountDTO accountDTO) {
        accountService.saveAccount(accountDTO);
        return accountDTO;
    }
    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable("id") int id) {
        accountService.deleteAccount(id);
        return "Account with ID " + id + " was deleted";
    }
}
