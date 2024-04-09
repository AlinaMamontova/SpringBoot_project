package com.example.springboot_project.service;

import com.example.springboot_project.dao.BankRepository;
import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.entity.Bank;
import com.example.springboot_project.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {

    private BankRepository bankRepository;
    private BankMapper bankMapper;


    @Autowired
    public BankService(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    public List<BankDTO> getAllBanks() {
        List<Bank> allBanks = bankRepository.findAll();
        List<BankDTO> banksDTOs = allBanks.stream().map(bankMapper::toDTO).toList();
        return banksDTOs;
    }

    public BankDTO saveBank(BankDTO bankDTO) {
        Bank bank = bankRepository.save(bankMapper.dtoToBank(bankDTO));
        return bankMapper.toDTO(bank);
    }

    public BankDTO getBank(int id) {
        Bank bank = null;
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            bank = optionalBank.get();
        }
        return bankMapper.toDTO(bank);
    }

    public void deleteBank(int id) {
        bankRepository.deleteById(id);
    }
}
