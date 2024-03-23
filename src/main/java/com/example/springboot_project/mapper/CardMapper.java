package com.example.springboot_project.mapper;

import com.example.springboot_project.dto.CardDTO;
import com.example.springboot_project.entity.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDTO toDTO(Card card);
    Card dtoToCard(CardDTO cardDTO);

}
