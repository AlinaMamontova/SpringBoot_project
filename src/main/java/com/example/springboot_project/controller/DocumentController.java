package com.example.springboot_project.controller;

import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/documents")
    public List<DocumentDTO> showAllDocuments() {
        List<DocumentDTO> alldocuments = documentService.getAllDocumentDTO();
        return alldocuments;
    }

    @GetMapping("/documents/{id}")
    public DocumentDTO getCurrencyById(@PathVariable("id") int id) {
        DocumentDTO documentDTO = documentService.getDocumentDTO(id);
        return documentDTO;
    }

    @PostMapping("/documents")
    public void addNewDocument(@RequestBody DocumentDTO documentDTO) {
        documentService.saveDocument(documentDTO);
    }

    @PutMapping("/documents")
    public DocumentDTO updateDocument(@RequestBody DocumentDTO documentDTO) {
        documentService.saveDocument(documentDTO);
        return documentDTO;
    }

    @DeleteMapping("/documents/{id}")
    @Secured("ADMIN")
    public String deleteDocument(@PathVariable("id") int id) {
        documentService.deleteDocument(id);
        return "Document with ID " + id + " was deleted";
    }
}
