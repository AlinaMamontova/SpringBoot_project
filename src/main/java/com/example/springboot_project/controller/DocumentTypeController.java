package com.example.springboot_project.controller;

import com.example.springboot_project.dto.DocumentTypeDTO;
import com.example.springboot_project.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentTypeController {
    private DocumentTypeService documentTypeService;

    @Autowired
    public DocumentTypeController(DocumentTypeService documentTypeService) {
        this.documentTypeService = documentTypeService;
    }

    @GetMapping("/documentTypes")
    public List<DocumentTypeDTO> showAllDocumentTypes() {
        List<DocumentTypeDTO> alldocumentTypes = documentTypeService.getAllDocumentTypesDTO();
        return alldocumentTypes;
    }

    @GetMapping("/documentTypes/{id}")
    public DocumentTypeDTO getDocumentTypeById(@PathVariable("id") int id) {
        DocumentTypeDTO documentTypeDTO = documentTypeService.getDocumentTypeDTO(id);
        return documentTypeDTO;
    }

    @PostMapping("/documentTypes")
    public void addNewDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.saveDocumentType(documentTypeDTO);
    }

    @PutMapping("/documentTypes")
    public DocumentTypeDTO updateDocumentType(@RequestBody DocumentTypeDTO documentTypeDTO) {
        documentTypeService.saveDocumentType(documentTypeDTO);
        return documentTypeDTO;
    }

    @DeleteMapping("/documentTypes/{id}")
    @Secured("ADMIN")
    public String deleteDocument(@PathVariable("id") int id) {
        documentTypeService.deleteDocumentType(id);
        return "DocumentType with ID " + id + " was deleted";
    }
}

