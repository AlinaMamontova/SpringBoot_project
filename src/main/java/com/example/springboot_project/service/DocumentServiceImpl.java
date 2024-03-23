package com.example.springboot_project.service;

import com.example.springboot_project.dao.DocumentDAO;
import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.entity.Document;
import com.example.springboot_project.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentDAO documentDAO;
    private DocumentMapper documentMapper;


    @Autowired
    public DocumentServiceImpl(DocumentDAO documentDAO, DocumentMapper documentMapper) {
        this.documentDAO = documentDAO;
        this.documentMapper = documentMapper;
    }

    @Override
    @Transactional
    public List<DocumentDTO> getAllDocumentDTO() {
        List<Document> allDocuments = documentDAO.getAllDocuments();
        List<DocumentDTO> documentsDTOs = allDocuments.stream().map(documentMapper::toDTO).toList();
        return documentsDTOs;
    }

    @Override
    @Transactional
    public void saveDocument(DocumentDTO documentDTO) {
        documentDAO.saveDocument(documentMapper.dtoToDocument(documentDTO));
    }

    @Override
    @Transactional
    public DocumentDTO getDocumentDTO(int id) {
        return documentMapper.toDTO(documentDAO.getDocument(id));
    }

    @Override
    @Transactional
    public void deleteDocument(int id) {
        documentDAO.deleteDocument(id);
    }
}
