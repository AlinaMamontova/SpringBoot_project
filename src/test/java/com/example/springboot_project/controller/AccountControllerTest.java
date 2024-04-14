package com.example.springboot_project.controller;

import com.example.springboot_project.dao.AccountRepository;
import com.example.springboot_project.dao.BankRepository;
import com.example.springboot_project.dao.ClientRepository;
import com.example.springboot_project.dao.CurrencyRepository;
import com.example.springboot_project.entity.Account;
import com.example.springboot_project.entity.Bank;
import com.example.springboot_project.entity.Client;
import com.example.springboot_project.entity.Currency;
import com.example.springboot_project.mapper.AccountMapper;
import com.example.springboot_project.util.ModelGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = {
                "spring.jpa.defer-datasource-initialization=false",//если true, то сначала настраиваются схемы, и только потом источник данных
                "spring.sql.init.mode=never"
        }
)
@AutoConfigureMockMvc
public class AccountControllerTest {
    //c помощью MockMvc мы будем выполнять http-запросы к AccountController,
    //как будто бы это реальный http-запрос
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelGenerator modelGenerator;
    private Account accountTest;
    private Client clientTest;
    private Bank bankTest;
    private Currency currencyTest;

    @BeforeEach
    void beforeEach() {

    }

    @Test
    @DisplayName("GET /accounts - получить список accounts")
    void testShowAllAccounts() throws Exception {
        mockMvc.perform(get("/accounts")
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER")))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("GET /accounts/{id} - получение account по id")
    void testGetAccountById() throws Exception {
        int id = 6;
        mockMvc.perform(get("/accounts/" + id)
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER")))
                .andDo(print())
                .andExpect(status().isOk());

    }
    //не работает
    @Test
    @DisplayName("POST /accounts - создание account")
    void testCreate() throws Exception {
        Account accountTest = new Account();
        String accountJson = objectMapper.writeValueAsString(accountTest);
        mockMvc.perform(post("/accounts")
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER"))
                        .content(accountJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    //не знаю как тестировать
    @Test
    @DisplayName("PUT /accounts/id -изменение bank по id")
    void testUpdate() throws Exception {

    }

    @Test
    @DisplayName("DELETE /accounts/id -изменение bank по id")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/accounts/{id}", 2)
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_ADMIN")))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}

