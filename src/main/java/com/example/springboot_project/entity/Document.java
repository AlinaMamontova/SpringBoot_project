package com.example.springboot_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "date_start", nullable = false)
    private LocalDate dateStart;
    @Column(name = "issue_organization", length = 255, nullable = false)
    private String issue_organization;
    @Column(name = "issue_code", nullable = false)
    private int issueCode;
    @Column(name = "document_status", nullable = false)
    private boolean documentStatus;
    @OneToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;
    @ManyToMany
    @JoinTable(
            name = "client_document",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients;
}
