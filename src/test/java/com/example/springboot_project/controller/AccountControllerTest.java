package com.example.springboot_project.controller;

import com.example.springboot_project.dao.AccountRepository;
import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.mapper.AccountMapper;
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
public class AccountControllerTest extends CommonTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Test
    @Order(0)
    @DisplayName("GET /accounts - получаем список accounts")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void listAll() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders.get("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()//ссылка на фактический объект MvcResult
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    @Override
    @Test
    @Order(1)
    @DisplayName("POST /accounts - создаем account")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void createObject() throws Exception {
        BankDTO bankDTO = new BankDTO();
        bankDTO.setId(1);
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setId(2);
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(3);
        AccountDTO newAccount = new AccountDTO(true, 200.8, bankDTO, currencyDTO, clientDTO);
        String result = mvc.perform(
                        MockMvcRequestBuilders.post("/accounts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(newAccount))
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
    @Transactional
    @DisplayName("PUT /accounts/{id} - изменяем account")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void updateObject() throws Exception {
        AccountDTO accountDTO = accountMapper.toDTO(accountRepository.findById(6).get());
        int id = accountDTO.getId();
        String result = mvc.perform(MockMvcRequestBuilders.put("/accounts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(accountDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString();
        log.info(result);
    }

    //тест проходит, но из БД не удаляется
    @Override
    @Test
    @Order(3)
    @Transactional
    @DisplayName("DELETE /accounts/{id} - удаляем account по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void deleteObject() throws Exception {
        String result = mvc.perform(MockMvcRequestBuilders
                        .delete("/accounts/{id}", "6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }


    @Override
    @Test
    @Order(4)
    @DisplayName("GET /accounts/{id} - получаем account по id")
    @WithMockUser(username = "polina@gmail.com", roles = "USER")
    protected void getById() throws Exception {
        int id = 1;
        String result = mvc.perform(MockMvcRequestBuilders.get("/accounts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn()//ссылка на фактический объект MvcResult
                .getResponse()
                .getContentAsString();
        log.info(result);
    }
}

