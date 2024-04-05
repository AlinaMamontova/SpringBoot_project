package com.example.springboot_project.controller;

import com.example.springboot_project.dto.CardTypeDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardTypes")
public class CardTypeController {
    private CardTypeService cardTypeService;

    @Autowired
    public CardTypeController(CardTypeService cardTypeService) {
        this.cardTypeService = cardTypeService;
    }

    @GetMapping
    public List<CardTypeDTO> showAllBCardTypes() {
        List<CardTypeDTO> allCardTypes = cardTypeService.getAllCardTypesDTOs();
        return allCardTypes;
    }

    @GetMapping("/{id}")
    public CardTypeDTO getCardTypeById(@PathVariable("id") int id) {
        CardTypeDTO cardTypeDTO = cardTypeService.getCardTypeDTO(id);
        if (cardTypeDTO == null) {
            throw new NoSuchElementException("There is no cardType with ID = " + id);
        }
        return cardTypeDTO;
    }

    @PostMapping
    public void create(@RequestBody CardTypeDTO cardTypeDTO) {
        cardTypeService.saveCardType(cardTypeDTO);
    }

    @PutMapping("/{id}")
    public CardTypeDTO update(@PathVariable int id, @RequestBody CardTypeDTO cardTypeDTO) {
        cardTypeDTO.setId(id);
        cardTypeService.saveCardType(cardTypeDTO);
        return cardTypeDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        cardTypeService.deleteCardType(id);
        return "CardType with ID " + id + " was deleted";
    }
}
