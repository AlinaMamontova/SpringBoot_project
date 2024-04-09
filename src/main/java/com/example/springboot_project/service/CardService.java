package com.example.springboot_project.service;

import com.example.springboot_project.dao.CardRepository;
import com.example.springboot_project.dto.CardDTO;
import com.example.springboot_project.entity.Card;
import com.example.springboot_project.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private CardRepository cardRepository;
    private CardMapper cardMapper;


    @Autowired
    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

    public List<CardDTO> getAllCards() {
        List<Card> allCards = cardRepository.findAll();
        List<CardDTO> cardsDTOs = allCards.stream().map(cardMapper::toDTO).toList();
        return cardsDTOs;
    }

    public void saveCard(CardDTO cardDTO) {
        cardRepository.save(cardMapper.dtoToCard(cardDTO));
    }

    public CardDTO getCard(int id) {
        Card card = null;
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            card = optionalCard.get();
        }
        return cardMapper.toDTO(card);
    }

    public void deleteCard(int id) {
        cardRepository.deleteById(id);
    }
}
