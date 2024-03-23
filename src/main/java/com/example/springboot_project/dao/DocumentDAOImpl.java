package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Document;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentDAOImpl implements DocumentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Document> getAllDocuments() {
        return entityManager.createQuery("from Document").getResultList();
    }

    @Override
    public void saveDocument(Document document) {
        Document newDocument = entityManager.merge(document);
        document.setId(newDocument.getId());
    }

    @Override
    public Document getDocument(int id) {
        return entityManager.find(Document.class, id);
    }

    @Override
    public void deleteDocument(int id) {
        entityManager.createQuery("delete from Document where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
