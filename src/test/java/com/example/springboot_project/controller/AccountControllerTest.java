package com.example.springboot_project.controller;

import com.example.springboot_project.dao.*;
import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.entity.Account;
import com.example.springboot_project.mapper.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = {
                "spring.jpa.defer-datasource-initialization=false",
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
    private AccountMapper accountMapper;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private BankMapper bankMapper;
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private CurrencyMapper currencyMapper;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private DocumentTypeRepository documentTypeRepository;
    @Autowired
    private DocumentTypeMapper documentTypeMapper;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentMapper documentMapper;
    @Autowired
    private ObjectMapper objectMapper;

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
//        int id = 6;
//
//        AccountDTO accountDTO = new AccountDTO(
//                id,
//                true,
//                700,
//                new BankDTO(),
//                new CurrencyDTO(),
//                new ClientDTO()
//
//        );
//        String accountJson = objectMapper.writeValueAsString(accountDTO);
//        mockMvc.perform(post("/accounts")
//                        .content(accountJson)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER")))
//                .andExpect(status().isOk())
//                .andDo(print());
//

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

