package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.DocumentTypeDTO;
import com.example.springboot_project.entity.DocumentType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentTypeMapper {

    DocumentTypeDTO toDTO(DocumentType documentType);
    DocumentType dtoToDocumentType(DocumentTypeDTO documentTypeDTO);

}
