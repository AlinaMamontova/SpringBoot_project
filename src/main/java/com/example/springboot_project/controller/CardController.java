package com.example.springboot_project.controller;

import com.example.springboot_project.dto.CardDTO;
import com.example.springboot_project.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public List<CardDTO> showAllBanks() {
        List<CardDTO> allCards = cardService.getAllCards();
        return allCards;
    }

    @GetMapping("/cards/{id}")
    public CardDTO getCardById(@PathVariable("id") int id) {
        CardDTO cardDTO = cardService.getCard(id);
        return cardService.getCard(id);
    }

    @PostMapping("/cards")
    public void addNewCard(@RequestBody CardDTO cardDTO) {
        cardService.saveCard(cardDTO);
    }

    @PutMapping("/cards")
    public CardDTO updateCard(@RequestBody CardDTO cardDTO) {
        cardService.saveCard(cardDTO);
        return cardDTO;
    }

    @DeleteMapping("/cards/{id}")
    public String deleteCard(@PathVariable("id") int id) {
        cardService.deleteCard(id);
        return "Card with ID " + id + " was deleted";
    }
}
