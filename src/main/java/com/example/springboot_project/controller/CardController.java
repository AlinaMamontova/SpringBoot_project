package com.example.springboot_project.controller;

import com.example.springboot_project.dto.CardDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<CardDTO> showAllBanks() {
        List<CardDTO> allCards = cardService.getAllCards();
        return allCards;
    }

    @GetMapping("/{id}")
    public CardDTO getCardById(@PathVariable("id") int id) {
        CardDTO cardDTO = cardService.getCard(id);
        if (cardDTO == null) {
            throw new NoSuchElementException("There is no card with ID = " + id);
        }
        return cardService.getCard(id);
    }

    @PostMapping
    public void create(@RequestBody CardDTO cardDTO) {
        cardService.saveCard(cardDTO);
    }

    @PutMapping("/{id}")
    public CardDTO update(@PathVariable int id, @RequestBody CardDTO cardDTO) {
        cardDTO.setId(id);
        cardService.saveCard(cardDTO);
        return cardDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        cardService.deleteCard(id);
        return "Card with ID " + id + " was deleted";
    }
}
