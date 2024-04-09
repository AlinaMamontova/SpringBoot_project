package com.example.springboot_project.controller;

import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public List<DocumentDTO> showAllDocuments() {
        List<DocumentDTO> alldocuments = documentService.getAllDocumentDTO();
        return alldocuments;
    }

    @GetMapping("/{id}")
    public DocumentDTO getCurrencyById(@PathVariable("id") int id) {
        DocumentDTO documentDTO = documentService.getDocumentDTO(id);
        if (documentDTO == null) {
            throw new NoSuchElementException("There is no document with ID = " + id);
        }
        return documentDTO;
    }

    @PostMapping
    public void create(@RequestBody DocumentDTO documentDTO) {
        documentService.saveDocument(documentDTO);
    }

    @PutMapping("/{id}")
    public DocumentDTO update(@PathVariable int id, @RequestBody DocumentDTO documentDTO) {
        documentDTO.setId(id);
        documentService.saveDocument(documentDTO);
        return documentDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        documentService.deleteDocument(id);
        return "Document with ID " + id + " was deleted";
    }
}
