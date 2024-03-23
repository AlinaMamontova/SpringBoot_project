package com.example.springboot_project.dao;

import com.example.springboot_project.entity.CardType;

import java.util.List;

public interface CardTypeDAO {
    List<CardType> getAllCardTypes();

    void saveCardType(CardType cardType);

    CardType getCardType(int id);

    void deleteCardType(int id);
}
