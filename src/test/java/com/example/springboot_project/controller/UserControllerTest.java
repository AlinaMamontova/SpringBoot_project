package com.example.springboot_project.controller;

import com.example.springboot_project.dao.UserRepository;
import com.example.springboot_project.entity.User;
import com.example.springboot_project.mapper.UserMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        properties = {
                "spring.jpa.defer-datasource-initialization=false",//
                "spring.sql.init.mode=never"
        }
)
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelGenerator modelGenerator;
    private User testUser;

    @BeforeEach
    public void beforeEach() {
        testUser = Instancio.of(modelGenerator.getUserModel()).create();
        assertDoesNotThrow(() -> userRepository.save(testUser));
    }
    @Test
    @DisplayName("POST /admin/users- создание user")
    void testCreate() throws Exception {
        User user = Instancio.of(modelGenerator.getUserModel())
                .create();
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/admin/users")
                        .content(userJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.user("ROLE_USER")))
                .andExpect(status().isOk())
                .andDo(print());
        var userFromDB = userRepository.findByEmail(user.getEmail());
        assertNotNull(user);
        assertEquals(user.getEmail(), userFromDB.get().getEmail());
    }
}
