package com.example.springboot_project.controller;

import com.example.springboot_project.dto.DocumentTypeDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.DocumentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentTypes")
@Tag(name = "API DocumentType", description = "Методы для работы с DocumentType")
public class DocumentTypeController {
    private DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @GetMapping
    @Operation(summary = "Получение всех documentTypes")
    public List<DocumentTypeDTO> showAllDocumentTypes() {
        List<DocumentTypeDTO> alldocumentTypes = documentTypeService.getAllDocumentTypesDTO();
        return alldocumentTypes;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение documentType по id")
    public DocumentTypeDTO getDocumentTypeById(@PathVariable("id") int id) {
        DocumentTypeDTO documentTypeDTO = documentTypeService.getDocumentTypeDTO(id);
        if (documentTypeDTO == null) {
            throw new NoSuchElementException("There is no documentType with ID = " + id);
        }
        return documentTypeDTO;
    }

    @PostMapping
    @Operation(summary = "Создание documentType")
    public void create(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.saveDocumentType(documentTypeDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение documentType по id")
    public DocumentTypeDTO update(@PathVariable int id, @RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeDTO.setId(id);
        documentTypeService.saveDocumentType(documentTypeDTO);
        return documentTypeDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Удаление documentType по id")
    public String delete(@PathVariable("id") int id) {
        documentTypeService.deleteDocumentType(id);
        return "DocumentType with ID " + id + " was deleted";
    }
}

