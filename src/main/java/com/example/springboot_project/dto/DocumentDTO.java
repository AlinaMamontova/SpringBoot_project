package com.example.springboot_project.dto;

import com.example.springboot_project.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private int id;
    private Date dateStart;
    private String issue_organization;
    private int issueCode;
    private boolean documentStatus;
    private DocumentTypeDTO documentType;
//    private Set<ClientDTO> clients; нужно или нет выводить список клиентов
}
