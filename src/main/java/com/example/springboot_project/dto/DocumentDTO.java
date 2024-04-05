package com.example.springboot_project.dto;

import com.example.springboot_project.entity.Client;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {
    private int id;
    private Date dateStart;
    private String issueOrganization;
    private int issueCode;
    private boolean documentStatus;
    private DocumentTypeDTO documentType;
    private List<ClientDTO> clients;
}
