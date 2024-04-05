package com.example.springboot_project.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//Ответ с токеном доступа
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponce {
    private String token;
}
