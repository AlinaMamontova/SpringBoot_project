package com.example.springboot_project.controller;

import com.example.springboot_project.dao.CardTypeRepository;
import com.example.springboot_project.dto.CardTypeDTO;
import com.example.springboot_project.mapper.CardTypeMapper;
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
public class CardTypeControllerTest extends CommonTest {
    @Autowired
    CardTypeRepository cardTypeRepository;
    @Autowired
    CardTypeMapper cardTypeMapper;

    @Override
    @Test
    @Order(0)
    @DisplayName("POST /cardTypes - создаем cardType")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void createObject() throws Exception {
        CardTypeDTO cardTypeDTO = new CardTypeDTO();
        cardTypeDTO.setTypeName("American Express");
        String result = mvc.perform(MockMvcRequestBuilders.post("/cardTypes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cardTypeDTO))
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
    @DisplayName("PUT /cardTypes/{id} - изменяем cardType")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void updateObject() throws Exception {
        CardTypeDTO cardTypeDTO = cardTypeMapper.toDTO(cardTypeRepository.findById(5).get());
        cardTypeDTO.setTypeName("JCB");
        int id = cardTypeDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.put("/cardTypes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(cardTypeDTO))
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
    @DisplayName("DELETE /cardTypes/{id} - изменяем cardType")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void deleteObject() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.delete("/cardTypes/{id}", "5")
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
    @DisplayName("GET /cardTypes - получаем список cardTypes")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void listAll() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get("/cardTypes")
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
    @DisplayName("GET /cardTypes/{id} - получаем cardType по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void getById() throws Exception {
        int id = 3;
        String result = mvc.perform(MockMvcRequestBuilders.get("/cardTypes/{id}", id)
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
