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
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private DocumentTypeRepository documentTypeRepository;
    private DocumentTypeMapper documentTypeMapper;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeRepository documentTypeRepository, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeRepository = documentTypeRepository;
        this.documentTypeMapper = documentTypeMapper;
    }

    @Override
    public List<DocumentTypeDTO> getAllDocumentTypesDTO() {
        List<DocumentType> allDocumentTypes = documentTypeRepository.findAll();
        List<DocumentTypeDTO> documentTypesDTOs = allDocumentTypes.stream().map(documentTypeMapper::toDTO).toList();
        return documentTypesDTOs;
    }

    @Override
    public void saveDocumentType(DocumentTypeDTO documentTypeDTO) {
        documentTypeRepository.save(documentTypeMapper.dtoToDocumentType(documentTypeDTO));
    }

    @Override
    public DocumentTypeDTO getDocumentTypeDTO(int id) {
        DocumentType documentType = null;
        Optional<DocumentType> optionalDocumentType = documentTypeRepository.findById(id);
        if (optionalDocumentType.isPresent()) {
            documentType = optionalDocumentType.get();
        }
        return documentTypeMapper.toDTO(documentType);
    }

    @Override
    public void deleteDocumentType(int id) {
        documentTypeRepository.deleteById(id);
    }
}
