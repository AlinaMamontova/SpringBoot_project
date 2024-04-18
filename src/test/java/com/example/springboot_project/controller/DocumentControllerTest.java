package com.example.springboot_project.controller;

import com.example.springboot_project.dao.DocumentRepository;
import com.example.springboot_project.dao.DocumentTypeRepository;
import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.dto.DocumentTypeDTO;
import com.example.springboot_project.mapper.DocumentMapper;
import com.example.springboot_project.mapper.DocumentTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class DocumentControllerTest extends CommonTest {
    @Autowired
    DocumentMapper documentMapper;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Autowired
    DocumentTypeMapper documentTypeMapper;

    @Override
    @Test
    @Order(0)
    @DisplayName("POST /document - создаем document")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void createObject() throws Exception {
        DocumentDTO documentDTO = new DocumentDTO();
        DocumentTypeDTO documentTypeDTO = documentTypeMapper.toDTO(documentTypeRepository.findById(3).get());
        documentDTO.setDateStart(new Date(System.currentTimeMillis()));
        documentDTO.setDocumentType(documentTypeDTO);
        documentDTO.setDocumentStatus(true);
        documentDTO.setIssueOrganization("Свидетельство о рождении");
        documentDTO.setIssueCode(112);

        String result = mvc.perform(MockMvcRequestBuilders.post("/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(documentDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    //не меняет
    @Override
    @Test
    @Order(1)
    @Transactional
    @DisplayName("PUT /documents - изменяем document")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void updateObject() throws Exception {
        DocumentDTO documentDTO = documentMapper.toDTO(documentRepository.findById(24).get());
        documentDTO.setIssueCode(1001);
        int id = documentDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.put("/documents/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(documentDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(2)
    @DisplayName("DELETE /documents - удаляем document")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void deleteObject() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.delete("/documents/{id}", "7")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(3)
    @DisplayName("GET /documents - получаем список documents")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void listAll() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get("/documents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(4)
    @DisplayName("GET /documents/{id} - получаем document по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void getById() throws Exception {
        int id = 24;
        String result = mvc.perform(MockMvcRequestBuilders.get("/documents/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }
}
