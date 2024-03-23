package com.example.springboot_project.dao;

import com.example.springboot_project.entity.DocumentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentTypeDAOImpl implements DocumentTypeDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        return entityManager.createQuery("from DocumentType").getResultList();
    }

    @Override
    public void saveDocumentType(DocumentType documentType) {
        DocumentType newDocumentType = entityManager.merge(documentType);
        documentType.setId(newDocumentType.getId());
    }

    @Override
    public DocumentType getDocumentType(int id) {
        return entityManager.find(DocumentType.class, id);
    }

    @Override
    public void deleteDocumentType(int id) {
        entityManager.createQuery("delete from DocumentType where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
