package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.entity.Currency;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDTO toDTO(Currency currency);
    Currency dtoToCurrency(CurrencyDTO currencyDTO);

}
