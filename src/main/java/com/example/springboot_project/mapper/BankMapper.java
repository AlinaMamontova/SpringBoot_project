package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.entity.Bank;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankMapper {

    BankDTO toDTO(Bank bank);
    Bank dtoToBank(BankDTO bankDTO);

}
