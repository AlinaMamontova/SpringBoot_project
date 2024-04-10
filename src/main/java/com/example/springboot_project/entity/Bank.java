package com.example.springboot_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank")
@ToString
@EqualsAndHashCode
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "bik", nullable = false)
    private int bik;
    @Column(name = "bank_name", length = 255, nullable = false)
    private String bankName;
    @Column(name = "country", length = 255, nullable = false)
    private String country;
    @Column(name = "city", length = 255, nullable = false)
    private String city;
}
