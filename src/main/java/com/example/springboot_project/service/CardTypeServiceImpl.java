package com.example.springboot_project.service;

import com.example.springboot_project.dao.CardTypeDAO;
import com.example.springboot_project.dto.CardTypeDTO;
import com.example.springboot_project.entity.CardType;
import com.example.springboot_project.mapper.CardTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardTypeServiceImpl implements CardTypeService {
    private CardTypeDAO cardTypeDAO;
    private CardTypeMapper cardTypeMapper;


    @Autowired
    public CardTypeServiceImpl(CardTypeDAO cardTypeDAO, CardTypeMapper cardTypeMapper) {
        this.cardTypeDAO = cardTypeDAO;
        this.cardTypeMapper = cardTypeMapper;
    }

    @Override
    @Transactional
    public List<CardTypeDTO> getAllCardTypesDTOs() {
        List<CardType> allCardTypes = cardTypeDAO.getAllCardTypes();
        List<CardTypeDTO> cardTypesDTOs = allCardTypes.stream().map(cardTypeMapper::toDTO).toList();
        return cardTypesDTOs;
    }

    @Override
    @Transactional
    public void saveCardType(CardTypeDTO cardTypeDTO) {
        cardTypeDAO.saveCardType(cardTypeMapper.dtoToCardType(cardTypeDTO));
    }

    @Override
    @Transactional
    public CardTypeDTO getCardTypeDTO(int id) {
        return cardTypeMapper.toDTO(cardTypeDAO.getCardType(id));
    }

    @Override
    @Transactional
    public void deleteCardType(int id) {
        cardTypeDAO.deleteCardType(id);
    }
}
