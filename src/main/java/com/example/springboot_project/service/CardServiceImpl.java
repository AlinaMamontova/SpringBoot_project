package com.example.springboot_project.service;

import com.example.springboot_project.dao.CardDAO;
import com.example.springboot_project.dto.CardDTO;
import com.example.springboot_project.entity.Card;
import com.example.springboot_project.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private CardDAO cardDAO;
    private CardMapper cardMapper;


    @Autowired
    public CardServiceImpl(CardDAO cardDAO, CardMapper cardMapper) {
        this.cardDAO = cardDAO;
        this.cardMapper = cardMapper;
    }

    @Override
    @Transactional
    public List<CardDTO> getAllCards() {
        List<Card> allCards = cardDAO.getAllCards();
        List<CardDTO> cardsDTOs = allCards.stream().map(cardMapper::toDTO).toList();
        return cardsDTOs;
    }

    @Override
    @Transactional
    public void saveCard(CardDTO cardDTO) {
        cardDAO.saveCard(cardMapper.dtoToCard(cardDTO));
    }

    @Override
    @Transactional
    public CardDTO getCard(int id) {
        CardDTO cardDTO = cardMapper.toDTO(cardDAO.getCard(id));
        return cardDTO;
    }

    @Override
    @Transactional
    public void deleteCard(int id) {
        cardDAO.deleteCard(id);
    }
}
