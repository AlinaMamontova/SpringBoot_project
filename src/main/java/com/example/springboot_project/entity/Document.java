package com.example.springboot_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document")
@ToString
@EqualsAndHashCode(exclude = "clients")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "date_start", nullable = false)
    private Date dateStart;
    @Column(name = "issue_organization", length = 255, nullable = false)
    private String issueOrganization;
    @Column(name = "issue_code", nullable = false)
    private int issueCode;
    @Column(name = "document_status", nullable = false)
    private boolean documentStatus;
    @OneToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "client_document",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private Set<Client> clients = new HashSet<>();

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }
}
