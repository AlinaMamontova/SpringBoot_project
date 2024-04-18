package com.example.springboot_project.controller;

import com.example.springboot_project.dao.CurrencyRepository;
import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.mapper.CurrencyMapper;
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
public class CurrencyControllerTest extends CommonTest {
    @Autowired
    CurrencyMapper currencyMapper;
    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    @Test
    @Order(0)
    @DisplayName("POST /currencies - создаем currency")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void createObject() throws Exception {
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setCurrencyName("Австралийский доллар");
        String result = mvc.perform(MockMvcRequestBuilders.post("/currencies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(currencyDTO))
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
    @DisplayName("PUT /currencies - изменяем currency")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void updateObject() throws Exception {
        CurrencyDTO currencyDTO = currencyMapper.toDTO(currencyRepository.findById(3).get());
        currencyDTO.setCurrencyName("Юань");
        int id = currencyDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.put("/currencies/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(currencyDTO))
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
    @DisplayName("DELETE /currencies - удаляем client")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void deleteObject() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.delete("/currencies/{id}", "4")
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
    @DisplayName("GET /currencies - получаем список currencies")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void listAll() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get("/currencies")
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
    @DisplayName("GET /currencies/{id} - получаем currency по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void getById() throws Exception {
        int id = 3;
        String result = mvc.perform(MockMvcRequestBuilders.get("/currencies/{id}", id)
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
