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
@Table(name = "card_type")
@ToString
@EqualsAndHashCode
public class CardType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "type_name", length = 255, nullable = false)
    private String typeName;

    public CardType(String typeName) {
        this.typeName = typeName;
    }
}
