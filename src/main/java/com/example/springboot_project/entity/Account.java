package com.example.springboot_project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "account")
@ToString(exclude = {"bank", "currency", "client", "cards"})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "account_status", nullable = false)
    private boolean accountStatus;
    @Column(name = "balance", nullable = false)
    private double balance;
    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
    @OneToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @OneToMany(mappedBy = "account")
    private List<Card> cards;
}
