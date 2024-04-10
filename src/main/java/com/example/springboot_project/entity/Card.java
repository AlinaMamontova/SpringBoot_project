package com.example.springboot_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
@ToString
@EqualsAndHashCode
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date_start")
    private Date dateStart;
    @Column(name = "date_end")
    private Date dateEnd;
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
    private CardType cardType;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

}
