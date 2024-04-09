package com.example.springboot_project.controller;

import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<CurrencyDTO> showAllClients() {
        List<CurrencyDTO> allCurrencies = currencyService.getAllCurrencyDTO();
        return allCurrencies;
    }

    @GetMapping("/{id}")
    public CurrencyDTO getCurrencyById(@PathVariable("id") int id) {
        CurrencyDTO currencyDTO = currencyService.getCurrencyDTO(id);
        if (currencyDTO == null) {
            throw new NoSuchElementException("There is no currency with ID = " + id);
        }
        return currencyDTO;
    }

    @PostMapping
    public void create(@RequestBody CurrencyDTO currencyDTO) {
        currencyService.saveCurrency(currencyDTO);
    }

    @PutMapping("/{id}")
    public CurrencyDTO update(@PathVariable int id, @RequestBody CurrencyDTO currencyDTO) {
        currencyDTO.setId(id);
        currencyService.saveCurrency(currencyDTO);
        return currencyDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        currencyService.deleteCurrency(id);
        return "Currency with ID " + id + " was deleted";
    }
}
