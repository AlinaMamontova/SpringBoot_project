package com.example.springboot_project.controller;

import com.example.springboot_project.dao.ClientRepository;
import com.example.springboot_project.dao.DocumentRepository;
import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.dto.DocumentDTO;
import com.example.springboot_project.mapper.ClientMapper;
import com.example.springboot_project.mapper.DocumentMapper;
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
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class ClientControllerTest extends CommonTest {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientMapper clientMapper;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    DocumentMapper documentMapper;

    @Override
    @Test
    @Order(0)
    @DisplayName("POST /clients - создаем client")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void createObject() throws Exception {
        ClientDTO clientDTO = new ClientDTO();
        DocumentDTO documentDTO = documentMapper.toDTO(documentRepository.findById(23).get());
        clientDTO.setFirstName("Egor");
        clientDTO.setLastName("Mironov");
        clientDTO.setPatronymic("Alexeevich");
        clientDTO.setDate(new Date(System.currentTimeMillis()));
        clientDTO.setDocuments(List.of(documentDTO));

        String result = mvc.perform(MockMvcRequestBuilders.post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO))
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
    @DisplayName("PUT /clients - изменяем client")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void updateObject() throws Exception {
        ClientDTO clientDTO = clientMapper.toDTO(clientRepository.findById(2).get());
        clientDTO.setFirstName("Adam");
        int id = clientDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.put("/clients/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(2)
    @DisplayName("DELETE /clients - удаляем client")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void deleteObject() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.delete("/clients/{id}", "19")
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
    @DisplayName("GET /clients - получаем список clients")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void listAll() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get("/clients")
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
    @DisplayName("GET /clients/{id} - получаем client по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void getById() throws Exception {
        int id = 10;
        String result = mvc.perform(MockMvcRequestBuilders.get("/clients/{id}", id)
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
