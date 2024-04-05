package com.example.springboot_project.dto;

import lombok.*;

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
