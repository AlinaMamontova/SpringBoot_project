package com.example.springboot_project.service;

import com.example.springboot_project.dto.CardDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getAllCards();

    void saveCard(CardDTO cardDTO);

    CardDTO getCard(int id);

    void deleteCard(int id);
}
