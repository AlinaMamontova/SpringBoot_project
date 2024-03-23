package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.entity.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    DocumentDTO toDTO(Document document);

    Document dtoToDocument(DocumentDTO documentDTO);

}
