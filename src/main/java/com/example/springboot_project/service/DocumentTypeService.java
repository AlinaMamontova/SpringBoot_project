package com.example.springboot_project.service;

import com.example.springboot_project.dao.DocumentTypeRepository;
import com.example.springboot_project.dto.DocumentTypeDTO;
import com.example.springboot_project.entity.DocumentType;
import com.example.springboot_project.mapper.DocumentTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentTypeService {
    private DocumentTypeRepository documentTypeRepository;
    private DocumentTypeMapper documentTypeMapper;

    @Autowired
    public DocumentTypeService(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentTypeMapper = documentTypeMapper;
    }

    public List<DocumentTypeDTO> getAllDocumentTypesDTO() {
        List<DocumentType> allDocumentTypes = documentTypeRepository.findAll();
        List<DocumentTypeDTO> documentTypesDTOs = allDocumentTypes.stream().map(documentTypeMapper::toDTO).toList();
        return documentTypesDTOs;
    }

    public void saveDocumentType(DocumentTypeDTO documentTypeDTO) {
        documentTypeRepository.save(documentTypeMapper.dtoToDocumentType(documentTypeDTO));
    }

    public DocumentTypeDTO getDocumentTypeDTO(int id) {
        DocumentType documentType = null;
        Optional<DocumentType> optionalDocumentType = documentTypeRepository.findById(id);
        if (optionalDocumentType.isPresent()) {
            documentType = optionalDocumentType.get();
        }
        return documentTypeMapper.toDTO(documentType);
    }

    public void deleteDocumentType(int id) {
        documentTypeRepository.deleteById(id);
    }
}
