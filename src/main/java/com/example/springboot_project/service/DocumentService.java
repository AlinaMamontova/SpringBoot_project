package com.example.springboot_project.service;

import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.entity.Document;

import java.util.List;

public interface DocumentService {
    List<DocumentDTO> getAllDocumentDTO();

    void saveDocument(DocumentDTO documentDTO);

    DocumentDTO getDocumentDTO(int id);

    void deleteDocument(int id);
}
