package com.example.springboot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {
    private int id;
    private Date dateStart;
    private Date dateEnd;
    private int cvc;
    private boolean cardStatus;
    private double balance;
    private ClientDTO client;
    private CardTypeDTO cardType;
    private AccountDTO account;
}
