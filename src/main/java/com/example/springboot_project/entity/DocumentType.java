package com.example.springboot_project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document_type")
@ToString
@EqualsAndHashCode
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "type_name", length = 255, nullable = false)
    private String typeName;

    public DocumentType(String typeName) {
        this.typeName = typeName;
    }
}
