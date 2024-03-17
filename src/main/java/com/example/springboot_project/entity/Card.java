package com.example.springboot_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date_start")
    private LocalDate dateStart;
    @Column(name = "date_end")
    private LocalDate dateEnd;
    @Column(name = "cvc")
    private int cvc;
    @Column(name = "card_status")
    private boolean cardStatus;
    @Column(name = "balance")
    private double balance;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private CardType cardTypeId;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
