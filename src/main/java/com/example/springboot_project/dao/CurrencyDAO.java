package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Currency;

import java.util.List;

public interface CurrencyDAO {
    List<Currency> getAllCurrencies();

    void saveCurrency(Currency currency);

    Currency getCurrency(int id);

    void deleteCurrency(int id);
}
