package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Card;

import java.util.List;

public interface CardDAO {
    List<Card> getAllCards();

    void saveCard(Card card);

    Card getCard(int id);

    void deleteCard(int id);
}
