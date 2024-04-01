package com.example.springboot_project.controller;

import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    public List<CurrencyDTO> showAllClients() {
        List<CurrencyDTO> allCurrencies = currencyService.getAllCurrencyDTO();
        return allCurrencies;
    }

    @GetMapping("/currencies/{id}")
    public CurrencyDTO getCurrencyById(@PathVariable("id") int id) {
        CurrencyDTO currencyDTO = currencyService.getCurrencyDTO(id);
        return currencyDTO;
    }

    @PostMapping("/currencies")
    public void addNewCurrency(@RequestBody CurrencyDTO currencyDTO) {
        currencyService.saveCurrency(currencyDTO);
    }

    @PutMapping("/currencies")
    public CurrencyDTO updateCurrency(@RequestBody CurrencyDTO currencyDTO) {
        currencyService.saveCurrency(currencyDTO);
        return currencyDTO;
    }

    @DeleteMapping("/currencies/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCurrency(@PathVariable("id") int id) {
        currencyService.deleteCurrency(id);
        return "Currency with ID " + id + " was deleted";
    }
}
