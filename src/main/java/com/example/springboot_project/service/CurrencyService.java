package com.example.springboot_project.service;

import com.example.springboot_project.dto.CurrencyDTO;

import java.util.List;

public interface CurrencyService {
    List<CurrencyDTO> getAllCurrencyDTO();

    void saveCurrency(CurrencyDTO currencyDTO);

    CurrencyDTO getCurrencyDTO(int id);

    void deleteCurrency(int id);
}
