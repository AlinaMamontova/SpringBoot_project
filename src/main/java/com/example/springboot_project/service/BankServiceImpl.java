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
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;
    private BankMapper bankMapper;


    @Autowired
    public BankServiceImpl(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    @Override
    public List<BankDTO> getAllBanks() {
        List<Bank> allBanks = bankRepository.findAll();
        List<BankDTO> banksDTOs = allBanks.stream().map(bankMapper::toDTO).toList();
        return banksDTOs;
    }

    @Override
    public void saveBank(BankDTO bankDTO) {
        bankRepository.save(bankMapper.dtoToBank(bankDTO));
    }

    @Override
    public BankDTO getBank(int id) {
        Bank bank = null;
        Optional<Bank> optionalBank = bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            bank = optionalBank.get();
        }
        return bankMapper.toDTO(bank);
    }

    @Override
    public void deleteBank(int id) {
        bankRepository.deleteById(id);
    }
}
