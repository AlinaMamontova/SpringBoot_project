package com.example.springboot_project.controller;

import com.example.springboot_project.dto.CardTypeDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.CardTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardTypes")
@Tag(name = "API CardType", description = "Методы для работы с CardTypes")
public class CardTypeController {
    private CardTypeService cardTypeService;

    @Autowired
    public CardTypeController(CardTypeService cardTypeService) {
        this.cardTypeService = cardTypeService;
    }

    @GetMapping
    @Operation(summary = "Получение всех cardTypes")
    public List<CardTypeDTO> showAllBCardTypes() {
        List<CardTypeDTO> allCardTypes = cardTypeService.getAllCardTypesDTOs();
        return allCardTypes;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение cardType по id")
    public CardTypeDTO getCardTypeById(@PathVariable("id") int id) {
        CardTypeDTO cardTypeDTO = cardTypeService.getCardTypeDTO(id);
        if (cardTypeDTO == null) {
            throw new NoSuchElementException("There is no cardType with ID = " + id);
        }
        return cardTypeDTO;
    }

    @PostMapping
    @Operation(summary = "Создание cardType")
    public void create(@RequestBody CardTypeDTO cardTypeDTO) {
        cardTypeService.saveCardType(cardTypeDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение cardType по id")
    public CardTypeDTO update(@PathVariable int id, @RequestBody CardTypeDTO cardTypeDTO) {
        cardTypeDTO.setId(id);
        cardTypeService.saveCardType(cardTypeDTO);
        return cardTypeDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление cardType по id")
    public String delete(@PathVariable("id") int id) {
        cardTypeService.deleteCardType(id);
        return "CardType with ID " + id + " was deleted";
    }
}
