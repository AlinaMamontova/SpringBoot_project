package com.example.springboot_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = {
        "spring.jpa.defer-datasource-initialization=false",
        "spring.sql.init.mode=never"})
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public abstract class CommonTest {
    @Autowired
    protected MockMvc mvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    protected abstract void createObject() throws Exception;

    protected abstract void updateObject() throws Exception;

    protected abstract void deleteObject() throws Exception;

    protected abstract void listAll() throws Exception;

    protected abstract void getById() throws Exception;
}
