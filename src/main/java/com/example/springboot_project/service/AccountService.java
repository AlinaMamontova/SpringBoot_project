package com.example.springboot_project.service;

import com.example.springboot_project.dao.AccountRepository;
import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.entity.Account;
import com.example.springboot_project.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private AccountMapper accountMapper;


    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }


    public List<AccountDTO> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        List<AccountDTO> accountsDTOs = allAccounts.stream().map(accountMapper::toDTO).toList();
        return accountsDTOs;
    }


    public AccountDTO saveAccount(AccountDTO accountDTO) {
        Account account = accountRepository.save(accountMapper.dtoToAccount(accountDTO));
        return accountMapper.toDTO(account);
    }


    public AccountDTO getAccount(int id) {
        Account account = null;
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        }
        return accountMapper.toDTO(account);
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

}
