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
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;
    private DocumentMapper documentMapper;


    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    @Override
    public List<DocumentDTO> getAllDocumentDTO() {
        List<Document> allDocuments = documentRepository.findAll();
        List<DocumentDTO> documentsDTOs = allDocuments.stream().map(documentMapper::toDTO).toList();
        return documentsDTOs;
    }

    @Override
    public void saveDocument(DocumentDTO documentDTO) {
        documentRepository.save(documentMapper.dtoToDocument(documentDTO));
    }

    @Override
    public DocumentDTO getDocumentDTO(int id) {
        Document document = null;
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            document = optionalDocument.get();
        }
        return documentMapper.toDTO(document);
    }

    @Override
    public void deleteDocument(int id) {
        documentRepository.deleteById(id);
    }
}
