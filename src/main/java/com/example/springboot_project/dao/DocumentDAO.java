package com.example.springboot_project.dao;

import com.example.springboot_project.entity.Document;

import java.util.List;

public interface DocumentDAO {
    List<Document> getAllDocuments();

    void saveDocument(Document document);

    Document getDocument(int id);

    void deleteDocument(int id);
}
