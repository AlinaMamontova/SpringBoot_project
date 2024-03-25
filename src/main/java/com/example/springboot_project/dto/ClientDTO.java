package com.example.springboot_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date date;
    private List<DocumentDTO> documents;
}
