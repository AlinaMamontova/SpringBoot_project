package com.example.springboot_project.service;

import com.example.springboot_project.dao.AccountDAO;
import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.entity.Account;
import com.example.springboot_project.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;
    private AccountMapper accountMapper;


    @Autowired
    public AccountServiceImpl(AccountDAO accountDAO, AccountMapper accountMapper) {
        this.accountDAO = accountDAO;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional
    public List<AccountDTO> getAllAccounts() {
        List<Account> allAccounts = accountDAO.getAllAccounts();
        List<AccountDTO> accountsDTOs = allAccounts.stream().map(accountMapper::toDTO).toList();
        return accountsDTOs;
    }

    @Override
    @Transactional
    public void saveAccount(AccountDTO accountDTO) {
        accountDAO.saveAccount(accountMapper.dtoToAccount(accountDTO));
    }

    @Override
    @Transactional
    public AccountDTO getAccount(int id) {
        return accountMapper.toDTO(accountDAO.getAccount(id));
    }

    @Override
    @Transactional
    public void deleteAccount(int id) {
        accountDAO.deleteAccount(id);
    }
}
