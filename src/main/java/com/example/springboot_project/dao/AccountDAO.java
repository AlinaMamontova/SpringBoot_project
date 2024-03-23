package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Account;

import java.util.List;

public interface AccountDAO {
    List<Account> getAllAccounts();

    void saveAccount(Account account);

    Account getAccount(int id);

    void deleteAccount(int id);
}
