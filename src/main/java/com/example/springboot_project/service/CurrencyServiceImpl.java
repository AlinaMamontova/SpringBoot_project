package com.example.springboot_project.service;

import com.example.springboot_project.dao.CurrencyDAO;
import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.entity.Currency;
import com.example.springboot_project.mapper.CurrencyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    private CurrencyDAO currencyDAO;
    private CurrencyMapper currencyMapper;


    @Autowired
    public CurrencyServiceImpl(CurrencyDAO currencyDAO, CurrencyMapper currencyMapper) {
        this.currencyDAO = currencyDAO;
        this.currencyMapper = currencyMapper;
    }

    @Override
    @Transactional
    public List<CurrencyDTO> getAllCurrencyDTO() {
        List<Currency> allCurrencies = currencyDAO.getAllCurrencies();
        List<CurrencyDTO> currenciesDTOs = allCurrencies.stream().map(currencyMapper::toDTO).toList();
        return currenciesDTOs;
    }

    @Override
    @Transactional
    public void saveCurrency(CurrencyDTO currencyDTO) {
        currencyDAO.saveCurrency(currencyMapper.dtoToCurrency(currencyDTO));
    }

    @Override
    @Transactional
    public CurrencyDTO getCurrencyDTO(int id) {
        return currencyMapper.toDTO(currencyDAO.getCurrency(id));
    }

    @Override
    @Transactional
    public void deleteCurrency(int id) {
        currencyDAO.deleteCurrency(id);
    }
}
