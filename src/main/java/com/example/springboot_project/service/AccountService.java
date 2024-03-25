package com.example.springboot_project.service;

import com.example.springboot_project.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccounts();

    void saveAccount(AccountDTO accountDTO);

    AccountDTO getAccount(int id);

    void deleteAccount(int id);
}

