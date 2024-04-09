package com.example.springboot_project.service;

import com.example.springboot_project.dao.CardTypeRepository;
import com.example.springboot_project.dto.CardTypeDTO;
import com.example.springboot_project.entity.CardType;
import com.example.springboot_project.mapper.CardTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardTypeService {
    private CardTypeRepository cardTypeRepository;
    private CardTypeMapper cardTypeMapper;


    @Autowired
    public CardTypeService(CardTypeRepository cardTypeRepository, CardTypeMapper cardTypeMapper) {
        this.cardTypeRepository = cardTypeRepository;
        this.cardTypeMapper = cardTypeMapper;
    }

    public List<CardTypeDTO> getAllCardTypesDTOs() {
        List<CardType> allCardTypes = cardTypeRepository.findAll();
        List<CardTypeDTO> cardTypesDTOs = allCardTypes.stream().map(cardTypeMapper::toDTO).toList();
        return cardTypesDTOs;
    }

    public void saveCardType(CardTypeDTO cardTypeDTO) {
        cardTypeRepository.save(cardTypeMapper.dtoToCardType(cardTypeDTO));
    }

    public CardTypeDTO getCardTypeDTO(int id) {
        CardType cardType = null;
        Optional<CardType> optionalCardType = cardTypeRepository.findById(id);
        if (optionalCardType.isPresent()) {
            cardType = optionalCardType.get();
        }
        return cardTypeMapper.toDTO(cardType);
    }

    public void deleteCardType(int id) {
        cardTypeRepository.deleteById(id);
    }
}
