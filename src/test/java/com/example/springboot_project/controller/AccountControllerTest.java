package com.example.springboot_project.controller;

import com.example.springboot_project.dto.AccountDTO;
import com.example.springboot_project.dto.BankDTO;
import com.example.springboot_project.dto.ClientDTO;
import com.example.springboot_project.dto.CurrencyDTO;
import com.example.springboot_project.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountControllerTest {
    //c помощью MockMvc мы будем выполнять http-запросы к AccountController,
    //как будто бы это реальный http-запрос

    private MockMvc mockMvc;

    //активируется перед каждым тестовым случаем, инициализируя MockMvc
    @InjectMocks //автоматически внедрит @Mock в тестируемый класс
    //все вызовы к AccountService будут переадресованы на Mock объект
    private AccountController accountController;
    @Mock
    private AccountService mockAccountService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
        //с помощью MockMvcBuilders.standaloneSetup мы можем тестировать
        //конкретные контроллеры, не затрагивая остальные части приложения
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    @DisplayName("GET /accounts - получить список accounts")
    void testShowAllAccounts() throws Exception {
        List<AccountDTO> accountDTOs = Collections.emptyList();
        Mockito.doReturn(accountDTOs).when(mockAccountService).getAllAccounts();
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andDo(print());
        verify(mockAccountService, Mockito.times(1)).getAllAccounts();
    }

    @Test
    @DisplayName("GET /accounts/{id} - получение account по id")
    void testGetAccountById() throws Exception {
        int id = 6;
        AccountDTO accountDTO = new AccountDTO(
                id,
                true,
                700,
                new BankDTO(),
                new CurrencyDTO(),
                new ClientDTO()
        );

        Mockito.doReturn(accountDTO).when(mockAccountService).getAccount(id);

        mockMvc.perform(get("/accounts/" + id))
                .andDo(print())
                .andExpect(status().isOk());
        verify(mockAccountService, Mockito.times(1)).getAccount(id);
    }

    @Test
    @DisplayName("POST /accounts - создание account")
    void testCreate() throws Exception {
        int id = 6;
        AccountDTO accountDTO = new AccountDTO(
                id,
                true,
                700,
                new BankDTO(),
                new CurrencyDTO(),
                new ClientDTO()
        );
        when(mockAccountService.saveAccount(any(AccountDTO.class))).thenReturn(accountDTO);
        String accountJson = objectMapper.writeValueAsString(accountDTO);
        mockMvc.perform(post("/accounts")
                        .content(accountJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    void testUpdate() throws Exception {
        int id = 6;
        AccountDTO accountDTO = new AccountDTO(
                id,
                true,
                800,
                new BankDTO(),
                new CurrencyDTO(),
                new ClientDTO()
        );
        when(mockAccountService.saveAccount(any(AccountDTO.class))).thenReturn(accountDTO);
        String accountJson = objectMapper.writeValueAsString(accountDTO);
        mockMvc.perform(put("/accounts/{id}", accountDTO.getId())
                        .content(accountJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/accounts/{id}", 5)
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_ADMIN")))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}

