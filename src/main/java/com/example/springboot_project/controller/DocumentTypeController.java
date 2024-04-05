package com.example.springboot_project.controller;

import com.example.springboot_project.dto.DocumentTypeDTO;
import com.example.springboot_project.exception.NoSuchElementException;
import com.example.springboot_project.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentTypes")
public class DocumentTypeController {
    private DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @GetMapping
    public List<DocumentTypeDTO> showAllDocumentTypes() {
        List<DocumentTypeDTO> alldocumentTypes = documentTypeService.getAllDocumentTypesDTO();
        return alldocumentTypes;
    }

    @GetMapping("/{id}")
    public DocumentTypeDTO getDocumentTypeById(@PathVariable("id") int id) {
        DocumentTypeDTO documentTypeDTO = documentTypeService.getDocumentTypeDTO(id);
        if (documentTypeDTO == null) {
            throw new NoSuchElementException("There is no documentType with ID = " + id);
        }
        return documentTypeDTO;
    }

    @PostMapping
    public void create(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.saveDocumentType(documentTypeDTO);
    }

    @PutMapping("/{id}")
    public DocumentTypeDTO update(@PathVariable int id, @RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeDTO.setId(id);
        documentTypeService.saveDocumentType(documentTypeDTO);
        return documentTypeDTO;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable("id") int id) {
        documentTypeService.deleteDocumentType(id);
        return "DocumentType with ID " + id + " was deleted";
    }
}

