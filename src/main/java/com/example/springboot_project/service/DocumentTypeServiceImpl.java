package com.example.springboot_project.service;

import com.example.springboot_project.dao.DocumentTypeDAO;
import com.example.springboot_project.dto.DocumentTypeDTO;
import com.example.springboot_project.entity.DocumentType;
import com.example.springboot_project.mapper.DocumentTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private DocumentTypeDAO documentTypeDAO;
    private DocumentTypeMapper documentTypeMapper;

    @Autowired
    public DocumentTypeServiceImpl(DocumentTypeDAO documentTypeDAO, DocumentTypeMapper documentTypeMapper) {
        this.documentTypeDAO = documentTypeDAO;
        this.documentTypeMapper = documentTypeMapper;
    }

    @Override
    @Transactional
    public List<DocumentTypeDTO> getAllDocumentTypesDTO() {
        List<DocumentType> allDocumentTypes = documentTypeDAO.getAllDocumentTypes();
        List<DocumentTypeDTO> documentTypesDTOs = allDocumentTypes.stream().map(documentTypeMapper::toDTO).toList();
        return documentTypesDTOs;
    }

    @Override
    @Transactional
    public void saveDocumentType(DocumentTypeDTO documentTypeDTO) {
        documentTypeDAO.saveDocumentType(documentTypeMapper.dtoToDocumentType(documentTypeDTO));
    }

    @Override
    @Transactional
    public DocumentTypeDTO getDocumentTypeDTO(int id) {
        return documentTypeMapper.toDTO(documentTypeDAO.getDocumentType(id));
    }

    @Override
    @Transactional
    public void deleteDocumentType(int id) {
        documentTypeDAO.deleteDocumentType(id);
    }
}
