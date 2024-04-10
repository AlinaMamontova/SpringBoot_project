package com.example.springboot_project.controller;

import com.example.springboot_project.dao.BankRepository;
import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.entity.Bank;
import com.example.springboot_project.mapper.BankMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = {
                "spring.jpa.defer-datasource-initialization=false",//Cхема автоматическую генерироваться Hibernate не будет
                "spring.sql.init.mode=never"//не будем инициализировать БД
        }
)
@AutoConfigureMockMvc
public class BankControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("GET /banks - получить список banks")
    void testGetAllBanks() throws Exception {
        mockMvc.perform(get("/banks")
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("GET /banks/{id} - получить bank по id")
    void testGetBankById() throws Exception {
        int id = 2;
        mockMvc.perform(get("/banks/{id}", id)
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("POST /banks - создание bank")
    void testCreate() throws Exception {
        int id = 7;
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(id);
        bankDTO.setBankName("Some bank");
        bankDTO.setCity("Moscow");
        bankDTO.setCountry("Russia");
        bankDTO.setBik(334);
        String bankJson = objectMapper.writeValueAsString(bankDTO);
        mockMvc.perform(post("/banks")
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER"))
                        .content(bankJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    //не знаю как тестировать
    @Test
    @DisplayName("PUT /banks/id -изменение bank по id")
    void testUpdate() throws Exception {

    }

    @Test
    @DisplayName("DELETE /banks/id -изменение bank по id")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/banks/{id}", 9)
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_ADMIN")))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}
