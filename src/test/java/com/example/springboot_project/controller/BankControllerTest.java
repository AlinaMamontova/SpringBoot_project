package com.example.springboot_project.controller;

import com.example.springboot_project.dao.BankRepository;
import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.entity.Bank;
import com.example.springboot_project.mapper.BankMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = {
                "spring.jpa.defer-datasource-initialization=false",//
                "spring.sql.init.mode=never"
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
    private BankDTO bankDTOtest;
    @Autowired
    private BankMapper bankMapper;
    private Faker faker;

//    @BeforeEach
//    void beforeEach() {
//        bankDTOtest = Instancio.of(BankDTO.class)
//                .ignore(Select.field(BankDTO.class, "id"))
//                .create();
//        assertDoesNotThrow(() -> bankRepository.save(bankMapper.dtoToBank(bankDTOtest)));
//    }


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
        Bank bank = Instancio.of(Bank.class)
                .ignore(Select.field(Bank::getId))
                .create();
        String bankJson = objectMapper.writeValueAsString(bank);
        mockMvc.perform(post("/banks")
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER"))
                        .content(bankJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    //не работает
    @Test
    @DisplayName("PUT /banks/id - изменение bank по id")
    void testUpdate() throws Exception {
        bankDTOtest = Instancio.of(BankDTO.class)
                .ignore(Select.field(BankDTO.class, "id"))
                .create();
        bankRepository.save(bankMapper.dtoToBank(bankDTOtest));

        var data = new HashMap<>();
        data.put("bankName", bankDTOtest.getBankName());
        mockMvc.perform(put("/banks/{id}", bankDTOtest.getId())
                        .content(objectMapper.writeValueAsString(data))
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER")))
                .andExpect(status().isOk())
                .andDo(print());
        var bankDTOtestFromRepo = bankRepository.findById(bankDTOtest.getId());
        Assertions.assertEquals("SSS", bankDTOtest.getBankName());

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
