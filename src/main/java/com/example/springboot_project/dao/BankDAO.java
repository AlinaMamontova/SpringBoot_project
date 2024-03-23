package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Bank;

import java.util.List;

public interface BankDAO {
    List<Bank> getAllBanks();

    void saveBank(Bank bank);

    Bank getBank(int id);

    void deleteBank(int id);
}
