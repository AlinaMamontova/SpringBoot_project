package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDTO toDTO(Account account);

    Account dtoToAccount(AccountDTO accountDTO);

}
