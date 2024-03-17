package com.example.springboot_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
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
    @OneToMany(mappedBy = "client")
    private List<Card> cards;
    @OneToMany(mappedBy = "client")
    private List<Account> accounts;
    @ManyToMany
    @JoinTable(
            name = "client_document",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "document_id")
    )
    private List<Document> documents;

}
