package com.example.springboot_project.controller;

import com.example.springboot_project.dao.BankRepository;
import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.mapper.BankMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class BankControllerTest extends CommonTest {
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BankMapper bankMapper;

    @Override
    @Test
    @Order(0)
    @DisplayName("POST /banks - создаем bank")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void createObject() throws Exception {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setBik(332);
        bankDTO.setBankName("Santander");
        bankDTO.setCountry("Spain");
        bankDTO.setCity("Malaga");
        String result = mvc.perform(MockMvcRequestBuilders.post("/banks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bankDTO))
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
    @DisplayName("PUT /banks/{id} - изменяем bank")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void updateObject() throws Exception {
        BankDTO bankDTO = bankMapper.toDTO(bankRepository.findById(4).get());
        int id = bankDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.put("/banks/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bankDTO))
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
    @DisplayName("DELETE /banks/{id} - удаляем bank")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void deleteObject() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders
                        .delete("/banks/{id}", "7")
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
    @DisplayName("GET /banks - получаем список accounts")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void listAll() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get("/banks")
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
    @DisplayName("GET /banks/{id} - получаем bank по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void getById() throws Exception {
        int id = 42;
        String result = mvc.perform(MockMvcRequestBuilders.get("/banks/{id}", id)
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
