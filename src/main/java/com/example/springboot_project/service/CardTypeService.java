package com.example.springboot_project.service;

import com.example.springboot_project.dto.CardTypeDTO;

import java.util.List;

public interface CardTypeService {
    List<CardTypeDTO> getAllCardTypesDTOs();

    void saveCardType(CardTypeDTO cardTypeDTO);

    CardTypeDTO getCardTypeDTO(int id);

    void deleteCardType(int id);
}
