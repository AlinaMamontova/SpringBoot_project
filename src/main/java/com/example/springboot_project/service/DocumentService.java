package com.example.springboot_project.service;

import com.example.springboot_project.dao.DocumentRepository;
import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.entity.Document;
import com.example.springboot_project.mapper.DocumentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;


    @Autowired
    public DocumentService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    public List<DocumentDTO> getAllDocumentDTO() {
        List<Document> allDocuments = documentRepository.findAll();
        List<DocumentDTO> documentsDTOs = allDocuments.stream().map(documentMapper::toDTO).toList();
        return documentsDTOs;
    }

    public void saveDocument(DocumentDTO documentDTO) {
        documentRepository.save(documentMapper.dtoToDocument(documentDTO));
    }

    public DocumentDTO getDocumentDTO(int id) {
        Document document = null;
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            document = optionalDocument.get();
        }
        return documentMapper.toDTO(document);
    }

    public void deleteDocument(int id) {
        documentRepository.deleteById(id);
    }
}
