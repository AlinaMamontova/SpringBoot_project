package com.example.springboot_project.controller;

import com.example.springboot_project.dao.CardRepository;
import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.dto.CardDTO;
import com.example.springboot_project.dto.CardTypeDTO;
import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.mapper.CardMapper;
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
public class CardControllerTest extends CommonTest {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardMapper cardMapper;

    @Override
    @Test
    @Order(0)
    @DisplayName("POST /cards - создаем cardType")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void createObject() throws Exception {
        CardDTO cardDTO = new CardDTO();
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(4);
        CardTypeDTO cardTypeDTO = new CardTypeDTO();
        cardTypeDTO.setId(2);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(7);
        cardDTO.setCardStatus(true);
        cardDTO.setClient(clientDTO);
        cardDTO.setCardType(cardTypeDTO);
        cardDTO.setAccount(accountDTO);
        cardDTO.setBalance(200);
        cardDTO.setCvc(1003);
        cardDTO.setDateEnd(new Date(System.currentTimeMillis() + 1000));
        cardDTO.setDateStart(new Date(System.currentTimeMillis()));

        String result = mvc.perform(MockMvcRequestBuilders.post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cardDTO))
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
    @DisplayName("PUT /cards/{id} - изменяем card")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void updateObject() throws Exception {
        CardDTO cardDTO = cardMapper.toDTO(cardRepository.findById(11).get());
        int id = cardDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.put("/cards/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cardDTO))
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
    @DisplayName("DELETE /cards/{id} - удаляем bank")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void deleteObject() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders
                        .delete("/cards/{id}", "6")
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
    @DisplayName("GET /cards - получаем список cards")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void listAll() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get("/cards")
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
    @DisplayName("GET /cards/{id} - получаем card по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void getById() throws Exception {
        int id = 10;
        String result = mvc.perform(MockMvcRequestBuilders.get("/cards/{id}", id)
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
