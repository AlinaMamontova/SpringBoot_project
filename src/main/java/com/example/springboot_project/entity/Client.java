package com.example.springboot_project.entity;

import com.example.springboot_project.dto.DocumentDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "first_name", length = 255, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;
    @Column(name = "patronymic", length = 255, nullable = false)
    private String patronymic;
    @Column(name = "birthdate", nullable = false)
    private Date date;
    @ManyToMany
    @JoinTable(
            name = "client_document",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<Document> documents = new ArrayList<>();

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
    }

}
