package com.example.springboot_project.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DocumentDTO{
    private int id;
    private Date dateStart;
    private String issueOrganization;
    private int issueCode;
    private boolean documentStatus;
    private DocumentTypeDTO documentType;
}
