package com.example.springboot_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "account_status", nullable = false)
    private boolean accountStatus;
    @Column(name = "balance", nullable = false)
    private double balance;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;
    @OneToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}
