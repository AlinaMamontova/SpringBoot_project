package com.example.springboot_project.service;

import com.example.springboot_project.dto.DocumentTypeDTO;
import com.example.springboot_project.entity.DocumentType;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentTypeDTO> getAllDocumentTypesDTO();

    void saveDocumentType(DocumentTypeDTO documentTypeDTO);

    DocumentTypeDTO getDocumentTypeDTO(int id);

    void deleteDocumentType(int id);
}
