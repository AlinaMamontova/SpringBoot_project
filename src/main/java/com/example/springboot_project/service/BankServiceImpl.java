package com.example.springboot_project.service;

import com.example.springboot_project.dao.BankDAO;
import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.entity.Bank;
import com.example.springboot_project.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    private BankDAO bankDAO;
    private BankMapper bankMapper;


    @Autowired
    public BankServiceImpl(BankDAO bankDAO, BankMapper bankMapper) {
        this.bankDAO = bankDAO;
        this.bankMapper = bankMapper;
    }

    @Override
    @Transactional
    public List<BankDTO> getAllBanks() {
        List<Bank> allBanks = bankDAO.getAllBanks();
        List<BankDTO> banksDTOs = allBanks.stream().map(bankMapper::toDTO).toList();
        return banksDTOs;
    }

    @Override
    @Transactional
    public void saveBank(BankDTO bankDTO) {
        bankDAO.saveBank(bankMapper.dtoToBank(bankDTO));
    }

    @Override
    @Transactional
    public BankDTO getBank(int id) {
        return bankMapper.toDTO(bankDAO.getBank(id));
    }

    @Override
    @Transactional
    public void deleteBank(int id) {
        bankDAO.deleteBank(id);
    }
}
