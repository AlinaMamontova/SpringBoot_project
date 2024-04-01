package com.example.springboot_project.controller;

import com.example.springboot_project.dto.CardTypeDTO;
import com.example.springboot_project.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardTypeController {
    private CardTypeService cardTypeService;

    @Autowired
    public CardTypeController(CardTypeService cardTypeService) {
        this.cardTypeService = cardTypeService;
    }

    @GetMapping("/cardTypes")
    public List<CardTypeDTO> showAllBCardTypes() {
        List<CardTypeDTO> allCardTypes = cardTypeService.getAllCardTypesDTOs();
        return allCardTypes;
    }

    @GetMapping("/cardTypes/{id}")
    public CardTypeDTO getCardTypeById(@PathVariable("id") int id) {
        CardTypeDTO cardTypeDTO = cardTypeService.getCardTypeDTO(id);
        return cardTypeDTO;
    }

    @PostMapping("/cardTypes")
    public void addNewCardType(@RequestBody CardTypeDTO cardTypeDTO) {
        cardTypeService.saveCardType(cardTypeDTO);
    }

    @PutMapping("/cardTypes")
    public CardTypeDTO updateCardType(@RequestBody CardTypeDTO cardTypeDTO) {
        cardTypeService.saveCardType(cardTypeDTO);
        return cardTypeDTO;
    }

    @DeleteMapping("/cardTypes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCardType(@PathVariable("id") int id) {
        cardTypeService.deleteCardType(id);
        return "CardType with ID " + id + " was deleted";
    }
}
