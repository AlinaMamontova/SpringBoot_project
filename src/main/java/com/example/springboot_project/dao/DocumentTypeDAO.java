package com.example.springboot_project.dao;

import com.example.springboot_project.entity.DocumentType;

import java.util.List;

public interface DocumentTypeDAO {
    List<DocumentType> getAllDocumentTypes();

    void saveDocumentType(DocumentType documentType);

    DocumentType getDocumentType(int id);

    void deleteDocumentType(int id);
}
