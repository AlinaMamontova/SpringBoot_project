package com.example.springboot_project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CardTypeDTO {
    private int id;
    private String typeName;
}
