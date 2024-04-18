package com.example.springboot_project.controller;

import com.example.springboot_project.dao.DocumentTypeRepository;
import com.example.springboot_project.dto.DocumentTypeDTO;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class DocumentTypeControllerTest extends CommonTest {
    @Autowired
    DocumentTypeRepository documentTypeRepository;
    @Autowired
    DocumentTypeMapper documentTypeMapper;

    @Override
    @Test
    @Order(0)
    @DisplayName("POST /documentTypes - создаем documentType")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void createObject() throws Exception {
        DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
        documentTypeDTO.setTypeName("DNI");
        String result = mvc.perform(MockMvcRequestBuilders.post("/documentTypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(documentTypeDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(1)
    @Transactional
    @DisplayName("PUT /documentTypes - изменяем documentType")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void updateObject() throws Exception {
        DocumentTypeDTO documentTypeDTO = documentTypeMapper.toDTO(documentTypeRepository.findById(7).get());
        documentTypeDTO.setTypeName("dni");
        int id = documentTypeDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.put("/documentTypes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(documentTypeDTO))
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
    @DisplayName("DELETE /documentTypes - удаляем documentType")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void deleteObject() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.delete("/documentTypes/{id}", "7")
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
    @DisplayName("GET /documentTypes - получаем список documentTypes")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void listAll() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get("/documentTypes")
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
    @DisplayName("GET /documentTypes/{id} - получаем documentTypes по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void getById() throws Exception {
        int id = 6;
        String result = mvc.perform(MockMvcRequestBuilders.get("/documentTypes/{id}", id)
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
