package com.example.springboot_project.service;

import com.example.springboot_project.dto.BankDTO;

import java.util.List;

public interface BankService {
    List<BankDTO> getAllBanks();

    void saveBank(BankDTO bankDTO);

    BankDTO getBank(int id);

    void deleteBank(int id);
}
