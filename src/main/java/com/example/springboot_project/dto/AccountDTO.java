package com.example.springboot_project.dto;

import com.example.springboot_project.entity.Bank;
import com.example.springboot_project.entity.Card;
import com.example.springboot_project.entity.Client;
import com.example.springboot_project.entity.Currency;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private int id;
    private boolean accountStatus;
    private double balance;
    private BankDTO bank;
    private CurrencyDTO currency;
    private ClientDTO client;
}
