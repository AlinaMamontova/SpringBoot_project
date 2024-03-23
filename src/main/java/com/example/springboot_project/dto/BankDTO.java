package com.example.springboot_project.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {
    private int id;
    private int bik;
    private String bankName;
    private String country;
    private String city;
}
