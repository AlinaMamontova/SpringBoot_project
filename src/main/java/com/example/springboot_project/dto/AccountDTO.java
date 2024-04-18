package com.example.springboot_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Schema(description = "Информация об account")
public class AccountDTO {
    @Schema(description = "Идентификатор account")
    private Integer id;
    @Schema(description = "Cтатус account")
    private boolean accountStatus;
    @Schema(description = "Баланс account")
    private double balance;
    @Schema(description = "Банк")
    private BankDTO bank;
    @Schema(description = "Валюта")
    private CurrencyDTO currency;
    @Schema(description = "Клиент")
    private ClientDTO client;

    public AccountDTO(boolean accountStatus, double balance, BankDTO bank, CurrencyDTO currency, ClientDTO client) {
        this.accountStatus = accountStatus;
        this.balance = balance;
        this.bank = bank;
        this.currency = currency;
        this.client = client;
    }
}
