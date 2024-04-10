package com.example.springboot_project.controller;

import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
@Tag(name = "API Document", description = "Методы для работы с Document")
public class DocumentController {
    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    @Operation(summary = "Получение всех documents")
    public List<DocumentDTO> showAllDocuments() {
        List<DocumentDTO> alldocuments = documentService.getAllDocumentDTO();
        return alldocuments;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение document по id")
    public DocumentDTO getCurrencyById(@PathVariable("id") int id) {
        DocumentDTO documentDTO = documentService.getDocumentDTO(id);
        if (documentDTO == null) {
            throw new NoSuchElementException("There is no document with ID = " + id);
        }
        return documentDTO;
    }

    @PostMapping
    @Operation(summary = "Создание document")
    public void create(@RequestBody DocumentDTO documentDTO) {
        documentService.saveDocument(documentDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение document по id")
    public DocumentDTO update(@PathVariable int id, @RequestBody DocumentDTO documentDTO) {
        documentDTO.setId(id);
        documentService.saveDocument(documentDTO);
        return documentDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление document по id")
    public String delete(@PathVariable("id") int id) {
        documentService.deleteDocument(id);
        return "Document with ID " + id + " was deleted";
    }
}
